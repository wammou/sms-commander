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

package org.helenium.iot.smscommander.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;

/**
 * @author Walid Ammou < w.ammou@helenium.org >
 */

public class InboundNotification implements IInboundMessageNotification {

	private final static Logger LOGGER = LoggerFactory.getLogger(InboundNotification.class);

	public void process(final AGateway pGateway, final MessageTypes pMsgType, final InboundMessage pMsg) {

		if (pMsgType == MessageTypes.INBOUND){
			LOGGER.info(">>> New Inbound message detected from Gateway: " + pGateway.getGatewayId());
		}else if (pMsgType == MessageTypes.STATUSREPORT) {
			LOGGER.info(">>> New Inbound Status Report message detected from Gateway: " + pGateway.getGatewayId());
		}
		LOGGER.info(pGateway.getFrom() + "> " + pMsg.toString());
	}

}
