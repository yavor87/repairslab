package it.f2.gestRip.ui.messages;

import it.f2.gestRip.EnvConstants;
import it.f2.gestRip.EnvProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {

	private static final String BUNDLE_NAME_it = "it.f2.gestRip.ui.messages.Messages_it_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_en = "it.f2.gestRip.ui.messages.Messages_en_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_nl = "it.f2.gestRip.ui.messages.Messages_nl_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_es = "it.f2.gestRip.ui.messages.Messages_es_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_th = "it.f2.gestRip.ui.messages.Messages_th_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_fi = "it.f2.gestRip.ui.messages.Messages_fi_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_gr = "it.f2.gestRip.ui.messages.Messages_gr_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_pt = "it.f2.gestRip.ui.messages.Messages_pt_enc"; //$NON-NLS-1$
	private static final String BUNDLE_NAME_fr = "it.f2.gestRip.ui.messages.Messages_fr_enc"; //$NON-NLS-1$

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
			}else if(language.equals("th")){	
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_th,new Locale("th","TH"));
				Locale.setDefault(new Locale("th","TH"));
			}else if(language.equals("fi")){	
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_fi,new Locale("th","TH"));
				Locale.setDefault(new Locale("fi","FI"));
			}else if(language.equals("el")){	
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_gr,new Locale("el","GR"));
				Locale.setDefault(new Locale("el","GR"));
			}else if(language.equals("pt")){	
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_pt,new Locale("pt","PT"));
				Locale.setDefault(new Locale("pt","PT"));
			}else if(language.equals("fr")){	
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_fr,Locale.FRENCH);
				Locale.setDefault(Locale.FRENCH);
			}else {
				RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME_en,Locale.ENGLISH);
				Locale.setDefault(Locale.ENGLISH);
			}
		}
		return RESOURCE_BUNDLE;
	}
	
	public static String userGuideFileString() {
		
		List<String> supportedUserGuideLang = new ArrayList<String>();
		supportedUserGuideLang.add("en");
		supportedUserGuideLang.add("it");
		
		String language = EnvProperties.getInstance().getProperty(EnvProperties.LANGUAGE);
		if(language == null){
			language = Locale.getDefault().getLanguage();
			EnvProperties.getInstance().setProperty(EnvProperties.LANGUAGE, language);
		}
		if(supportedUserGuideLang.contains(language)){
			return EnvConstants.FILE_USER_GUIDE_PREFIX + language + EnvConstants.FILE_USER_GUIDE_SUFFIX;
		} else {
			return EnvConstants.FILE_USER_GUIDE_PREFIX + "en" + EnvConstants.FILE_USER_GUIDE_SUFFIX;
		}
	}

	public static HashMap<String, String> getLanguageMaps() {
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("en", Locale.ENGLISH.getDisplayLanguage()); //"English"
		result.put("it", Locale.ITALIAN.getDisplayLanguage()); //"Italiano"
		result.put("nl", new Locale("nl","NL").getDisplayLanguage()); // "Nederlands"
		result.put("es", new Locale("es","ES").getDisplayLanguage()); //"Español"
		result.put("th", new Locale("th","TH").getDisplayLanguage()); //"Thai"
		result.put("fi", new Locale("fi","FI").getDisplayLanguage()); //"Finnish"
		result.put("el", new Locale("el","GR").getDisplayLanguage()); //"Greek"
		result.put("pt", new Locale("pt","PT").getDisplayLanguage()); //"Portuguese"
		result.put("fr", Locale.FRENCH.getDisplayLanguage()); //"French"
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
