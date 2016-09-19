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

package org.helenium.iot.smscommander.sender;

import org.helenium.iot.smscommander.Gateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.OutboundMessage;
import org.smslib.Service;

/**
 * @author Walid Ammou < w.ammou@helenium.org >
 */

public class Sender {

	private final static Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	public void send(final String pMessage) throws Exception {

		final OutboundNotification outboundNotification = new OutboundNotification();
		final Gateway gateway = new Gateway();

		Service.getInstance().setOutboundMessageNotification(outboundNotification);
		Service.getInstance().addGateway(gateway);
		Service.getInstance().startService();

		for(final String recipient: Gateway.getRecipients()){

			LOGGER.info("Sending the following message to " + recipient);
			final OutboundMessage outboundMessage = new OutboundMessage(recipient, pMessage);
			LOGGER.info(recipient + "> " + outboundMessage.toString());
			Service.getInstance().sendMessage(outboundMessage);
			LOGGER.info("Done");
			LOGGER.info("");

		}
	}

}
