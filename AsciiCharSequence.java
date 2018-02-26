public class AsciiCharSequence implements CharSequence/* extends/iimplements */ {
    
    byte[] chars;

    public AsciiCharSequence(byte[] chars) {
    	this.chars = chars;
    
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int index) {
    	return (char)chars[0];
    }

    @Override
    public String toString() {
    	return new String(bytes, "ISO-8859-1");
    }

	@Override
	public CharSequence subSequence(int start, int end){  //Переопределение subSequence()
		return new AsciiCharSequence(Arrays.copyOfRange(chars, start, end));
	}
    // implementation
}