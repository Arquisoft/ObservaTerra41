package utils;

public class StripString {
	
	/**
	 * Corta los espacios del principio y final del string
	 * @param dato
	 * @return
	 */
	public static String Strip(String dato) {
		char[] data = dato.toCharArray();
		String strippedData = "";
		int init = 0;
		int end = data.length - 1;
		if (data.length > 0) {
			// find init
			for (int i = 0; i < data.length; i++) {
				if (data[i] != ' ') {
					init = i;
					break;
				}
			}

			// find end
			for (int i = end; i <= 0; i--) {
				if (data[i] != ' ') {
					end = i;
					break;
				}
			}

			// copy data
			for (int i = init; i <= end; i++) {
				strippedData += data[i];
			}
		}

		return strippedData;

	}

}
