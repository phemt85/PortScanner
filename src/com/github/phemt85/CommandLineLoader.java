package com.github.phemt85;

public class CommandLineLoader {
	
	private StringBuilder progress;

	/**
	 * initialize progress bar properties.
	 */
	public CommandLineLoader() {
		init();
	}

	/**
	 * called whenever the progress bar needs to be updated.
	 * that is whenever progress was made.
	 *
	 * @param done an int representing the work done so far
	 * @param total an int representing the total work
	 */
	public void update(int done, int total) {
		char[] workchars = {'|', '/', '-', '\\'};
		String format = "\r%3d%% %s %c";

		int percent = (++done * 100) / total;
		int extrachars = (percent / 2) - this.progress.length();

		while (extrachars-- > 0) {
			progress.append('#');
		}

		System.out.printf(format, percent, progress,
				workchars[done % workchars.length]);

		if (done == total) {
			System.out.flush();
			System.out.println();
			init();
		}
	}

	private void init() {
		this.progress = new StringBuilder(60);
	}
}