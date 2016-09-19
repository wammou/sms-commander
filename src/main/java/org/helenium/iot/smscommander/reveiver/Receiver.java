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

package org.helenium.iot.smscommander.reveiver;

import java.util.ArrayList;
import java.util.List;

import org.helenium.iot.smscommander.Gateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Service;

/**
 * @author Walid Ammou < w.ammou@helenium.org >
 */

public class Receiver {

	private final static Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	public void receive() throws Exception {

		// Create the notification callback method for inbound & status report messages.
		final InboundNotification inboundNotification = new InboundNotification();

		final Gateway gateway = new Gateway();

		try {
			// Set up the notification methods.
			Service.getInstance().setInboundMessageNotification(inboundNotification);

			// Add the Gateway to the Service object.
			Service.getInstance().addGateway(gateway);
			// Similarly, you may define as many Gateway objects, representing
			// various GSM modems, add them in the Service object and control all of them.
			// Start! (i.e. connect to all defined Gateways)
			Service.getInstance().startService();

			// Read Messages. The reading is done via the Service object and
			// affects all Gateway objects defined. This can also be more directed to a specific
			// Gateway - look the JavaDocs for information on the Service method calls.

			// Define a list which will hold the read messages.
			final List<InboundMessage> msgList = new ArrayList<InboundMessage>();
			Service.getInstance().readMessages(msgList, MessageClasses.ALL);

			for (final InboundMessage msg : msgList) {
				LOGGER.info(msg.toString());
				// TODO run an action depending on message content
			}

		}
		catch (final Exception pE) {
			LOGGER.error(pE.getMessage(), pE);
		} finally {
			Service.getInstance().stopService();
		}

	}

}
