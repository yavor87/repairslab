package it.f2.gestRip.util;

/**
 * AutocompletingCellEditor
 */

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

/**
 * Implementa un editor di cella con autocompletamento dei dati sulla base di un
 * elenco di possibili suggerimenti. Ovviamente si potrebbe implementare
 * direttamente su un JTextField e rendere quindi questa funzionalit�
 * disponibile anche sui singoli campi di testo.
 *
 * @author max
 */
public class AutocompletingCellEditor extends AbstractCellEditor implements
		TableCellEditor {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/** componente di modifica */
	JTextField editorComponent;

	/** elenco di scelte possibili */
	String[] choices;

	/** prefisso su cui elaborare un suggerimento */
	String prefix;

	/**
	 * Crea un nuovo oggetto impostando un vettore di possibili valori per
	 * questo campo di testo.
	 *
	 * @param suggestions
	 */
	public AutocompletingCellEditor(String[] suggestions) {
		choices = suggestions;
		createUI();
	}

	/**
	 * Crea l'interfacciautente costruendo il campo di testo JTextField su cui
	 * viene impostato un KeyListener. Questi eventi permettono di identificare
	 * le modifiche apportate dall'utente al campo di testo e di gestire anche
	 * il tasto backspace, che ha l'effetto di cancellare la selezione attiva.
	 */
	private void createUI() {
		editorComponent = new JTextField();
		editorComponent.setBorder(null);
		editorComponent.addKeyListener(new KeyAdapter() {
			@Override
            public void keyTyped(KeyEvent e) {
				// System.out.println(e);
				if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					deleteSelection();
				} else {
					suggest();
				}
			}
		});
	}

	/**
	 * Questo metodo viene invocato a seguito della pressione del tasto
	 * backspace, e comporta la cancellazione dal campo di testo del testo
	 * selezionato. Si noti la presenza dell'invocazione dei metodi
	 * setSelectionStart() e setSelectionEnd() che inizializzano lo stato di
	 * selezione del campo di testo.
	 */
	private void deleteSelection() {
		int selectionStart = editorComponent.getSelectionStart();
		String text = editorComponent.getText();
		text = text.substring(0, selectionStart);

//		System.out.println("deleteSelection(): selectionStart="
//				+ selectionStart);
//		System.out.println("deleteSelection(): text=" + text);

		editorComponent.setSelectionStart(0);
		editorComponent.setSelectionEnd(0);
		editorComponent.setText(text);
	}

	/**
	 * Determina un suggerimento per il valore attuale del testo e lo presenta
	 * nel campo di testo.
	 */
	private void suggest() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				suggest(findSuggestion());
			}
		});
	}

	/**
	 * Individua un suggerimento sulla base del testo presente attualmente nel
	 * campo di modifica. Per fare questo estrae quanto effettivamente digitato
	 * dall'utente, prendendo la parte iniziale del contenuto testuale del
	 * JTextField utilizzato, fino all'inizio della selezione. Quest'ultima
	 * identifica infatti l'inizio della parte "suggerita" dal sistema.
	 *
	 * La ricerca del suggerimento � poi sequenziale, nel caso esista pi� di un
	 * valore rispondente allo stesso prefisso viene preso il primo.
	 *
	 * @return il suggerimento da visualizzare
	 */
	private String findSuggestion() {
		prefix = editorComponent.getText();
		int selectionStart = editorComponent.getSelectionStart();
		if (selectionStart != -1) {
			prefix = prefix.substring(0, selectionStart);
		}
		System.out.println("findSuggestion(): prefix=" + prefix);

		if (prefix.length() > 0) {
			for (String choice : choices) {
				if (choice.startsWith(prefix)) {
					return choice;
				}
			}
		}
		return "";
	}

	/**
	 * Imposta un suggerimento nel campo di testo avendo l'accortezza di
	 * impostare la selezione sulla parte non digitata dall'utente, identificata
	 * dal fatto che non � presente nella stringa prefix.
	 *
	 * @param suggestion
	 */
	private void suggest(String suggestion) {
		System.out.println("suggest(): suggestion=" + suggestion);

		if (suggestion.length() > 0) {
			editorComponent.setText(suggestion);
			editorComponent.setSelectionStart(prefix.length());
			editorComponent.setSelectionEnd(suggestion.length());
		}
	}

	/**
	 * @return il valore di ritorno dell'editor di cella
	 */
	public Object getCellEditorValue() {
		return editorComponent.getText();
	}

	/**
	 * Questo metodo � specificato nell'interfaccia TableCellEditor e viene
	 * utilizzato da JTable per richiedere un componente da utilizzare per la
	 * modifica della cella ogni qual volta questa operazione si renda
	 * necessaria.
	 *
	 * @return componente di modifica della cella
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int col) {

		editorComponent.setText((String) value);
		return editorComponent;
	}

}