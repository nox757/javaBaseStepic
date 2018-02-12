public Label checkLabels(TextAnalyzer[] analyzers, String text) {


	for (TextAnalyzer analyzer : analyzers ) {

		Label label = analyzer.processText(text);
		
		if (label != Label.OK){
			return label; 
		}
	}

    return Label.OK;
}


class SpamAnalyzer extends KeywordAnalyzer {
	private String[] keywords;
	
	public SpamAnalyzer (String[] keywords) {
		this.keywords = keywords;
	}

	@Override
	protected String[] getKeywords() {
		return keywords;
	} 

	@Override
	protected Label getLabel() {

		return Label.SPAM;
	}

}
class NegativeTextAnalyzer extends KeywordAnalyzer {
	private String[] keywords = {":(", "=(", ":|"};

	@Override
	protected String[] getKeywords() {
		return keywords;
	} 

	@Override
	protected Label getLabel() {
		return Label.NEGATIVE_TEXT;
	}

}

class TooLongTextAnalyzer implements TextAnalyzer {
	private int maxLength;
	
	public TooLongTextAnalyzer (int maxLen) {
			this.maxLength = maxLen;
	}

	@Override
	public Label processText(String text) {
		return (text.length() <= this.maxLength) ? Label.OK : Label.TOO_LONG ;
	}

}


abstract class KeywordAnalyzer implements TextAnalyzer {
	protected abstract String[] getKeywords();
	protected abstract Label getLabel();

	@Override
	public Label processText(String text) {
		String[] keywords = getKeywords();
		for (String keyword : keywords) {
			if (text.toLowerCase().contains(keyword.toLowerCase()))
				return getLabel();
		}
		return Label.OK;
	}
}