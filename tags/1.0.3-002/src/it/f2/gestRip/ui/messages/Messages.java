package it.f2.gestRip.ui.messages;

import it.f2.gestRip.EnvProperties;

import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	private static final String BUNDLE_NAME_it = "it.f2.gestRip.ui.messages.Messages_it"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_en = "it.f2.gestRip.ui.messages.Messages_en"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_nl = "it.f2.gestRip.ui.messages.Messages_nl"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_es = "it.f2.gestRip.ui.messages.Messages_es"; //$NON-NLS-1$

	private static ResourceBundle RESOURCE_BUNDLE = null;

	public static final ResourceBundle getRESOURCE_BUNDLE(){
		if(RESOURCE_BUNDLE==null){
			//System.out.println("Country:"+Locale.getDefault().getCountry());
			String language = EnvProperties.getInstance().getProperty(EnvProperties.LANGUAGE);
			if(language == null){
				language = Locale.getDefault().getLanguage();
				EnvProperties.getInstance().setProperty(EnvProperties.LANGUAGE, language);
			}
			if(language.equals("it")){
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_it,Locale.ITALIAN);
				Locale.setDefault(Locale.ITALIAN);
			}else if(language.equals("nl")){
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_nl,new Locale("nl","NL"));
				Locale.setDefault(new Locale("nl","NL"));
			}else if(language.equals("es")){
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_es,new Locale("es","ES"));
				Locale.setDefault(new Locale("es","ES"));
			}else {
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_en,Locale.ENGLISH);
				Locale.setDefault(Locale.ENGLISH);
			}
		}
		return RESOURCE_BUNDLE;
	}

	public static HashMap<String, String> getLanguageMaps() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("en", "English");
		result.put("it", "Italiano");
		result.put("nl", "Nederlands");
		result.put("es", "Español");
		return result;
	}

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return getRESOURCE_BUNDLE().getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
