package net.sf.repairslab.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * Formatter per la valuta
 * @author Fabrizio Ferraiuolo
 * 22/set/2010
 * 17.20.39
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class CurrencyFormatter extends AbstractFormatter {
	
	private Locale locale;
	
	public CurrencyFormatter(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		Number number = null;
		NumberFormat curFormat = NumberFormat.getCurrencyInstance(locale);
		NumberFormat numFormat = NumberFormat.getNumberInstance(locale);
		try {
			number = curFormat.parse(text);
		} catch (ParseException ex) {
			try {
				number = numFormat.parse(text);
			} catch (ParseException ex2) {
				throw ex2;
			}
		}
		return number;
		
	}
	
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value == null) {
			return "";
		}
		NumberFormat curFormat = NumberFormat.getCurrencyInstance(locale);
		return curFormat.format(value);
		
	}
	
}
