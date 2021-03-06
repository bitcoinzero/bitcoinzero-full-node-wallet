/************************************************************************************************
 *  _________          _     ____          _           __        __    _ _      _   _   _ ___
 * |__  / ___|__ _ ___| |__ / ___|_      _(_)_ __   __ \ \      / /_ _| | | ___| |_| | | |_ _|
 *   / / |   / _` / __| '_ \\___ \ \ /\ / / | '_ \ / _` \ \ /\ / / _` | | |/ _ \ __| | | || |
 *  / /| |__| (_| \__ \ | | |___) \ V  V /| | | | | (_| |\ V  V / (_| | | |  __/ |_| |_| || |
 * /____\____\__,_|___/_| |_|____/ \_/\_/ |_|_| |_|\__, | \_/\_/ \__,_|_|_|\___|\__|\___/|___|
 *                                                 |___/
 *
 * Copyright (c) 2016 Ivan Vaklinov <ivan@vaklinov.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 **********************************************************************************/
package com.vaklinov.zcashui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Reporter for periodic errors. Will later have options to filter errors etc.
 *
 * @author Ivan Vaklinov <ivan@vaklinov.com>
 */
public class StatusUpdateErrorReporter
{
	private JFrame parent;
	private long lastReportedErrorTime = 0;

	public StatusUpdateErrorReporter(JFrame parent)
	{
		this.parent = parent;
	}

	public void reportError(Exception e)
	{
		reportError(e, true);
	}

	public void reportError(Exception e, boolean isDueToAutomaticUpdate)
	{
		Log.error("Unexpected error: ", e);

		// TODO: Error logging
		long time = System.currentTimeMillis();

		// TODO: More complex filtering/tracking in the future
		if (isDueToAutomaticUpdate && (time - lastReportedErrorTime) < (45 * 1000))
		{
			return;
		}

		if (isDueToAutomaticUpdate)
		{
			lastReportedErrorTime = time;
		}

		String settingsDirectory = ".BitcoinZeroSwingWallet";

		try
		{
			settingsDirectory = OSUtil.getSettingsDirectory();
		} catch (Exception e2)
		{
			Log.error("Secondary error: ", e2);
		}

		JOptionPane.showMessageDialog(
			parent,
			"An unexpected error occurred during the operation of the GUI wallet.\n" +
			"Details may be found in the log in directory: " + settingsDirectory + "\n" +
			"\n" +
			e.getMessage(),
			"Error in wallet operation.", JOptionPane.ERROR_MESSAGE);
	}
}
