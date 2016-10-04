/** The sms-commander project is initiated to provide possibility to command IoT objects via Short Message Service (SMS).
 *   Copyright (C) 2016 The sms-commander Authors.

 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 3 of the License, or
 *   (at your option) any later version.

 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.

 *   You should have received a copy of the GNU General Public License
 *   along with this program; if not, see <http://www.gnu.org/licenses/>
 *   or write to the Free Software Foundation, Inc., 51 Franklin Street,
 *   Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.helenium.iot.smscommander;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Walid Ammou < w.ammou@helenium.org >
 */

public class Launcher {

	private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

	public static void main( final String[] args ) {

		try {

			// Logging general configuration
			BasicConfigurator.configure();

			final Gateway gateway = new Gateway();

			// Send a message to recipients setted in the configuration file (to delete after test)
			Gateway.getSender().send("Hello, SMS send from GSM Modem!");

		} catch (final Exception pE) {
			LOGGER.error("Error: ", pE);
		}
	}
}
