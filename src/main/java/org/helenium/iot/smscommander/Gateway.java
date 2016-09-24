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

import java.io.IOException;
import java.util.Properties;

import org.helenium.iot.smscommander.reveiver.Receiver;
import org.helenium.iot.smscommander.sender.Sender;
import org.helenium.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 * @author Walid Ammou < w.ammou@helenium.org >
 */

public class Gateway extends SerialModemGateway {

	private final static Logger LOGGER = LoggerFactory.getLogger(Gateway.class);

	private static String IDENTIFIER;

	private static String SERIAL_PORT;

	private static Integer SERIAL_PORT_RATE;

	private static String GATEWAY_MANUFACTURER;

	private static String GATEWAY_MODEL;

	private static boolean IS_INBOUND;

	private static boolean IS_OUTBOUND;

	private static String GATEWAY_SMSC_NUMBER;

	private static String GETEWAY_SIM_PIN;

	private static String GETEWAY_SIM_PIN_2;

	private static String PROTOCOL;

	private static String[] recipients;

	private final static Sender sender = new Sender();

	private final static Receiver receiver = new Receiver();

	static {

		LOGGER.info("");
		LOGGER.info(" ___ _  _  ___  ___ ___  _  _  _  _   _   _  _  ___  ___ ___");
		LOGGER.info("|_-<| ./ ||_-< //  / _ .| ./ || ./ | /_. | .  || _ .|_ /| _ .".replace(".", "\\"));
		LOGGER.info("___/|_  _|___/ ..__.___/|_  _||_  _||_ _||_ ._||___/|__.|_ ..".replace(".", "\\"));
		LOGGER.info("Tool to command IoT objects via SMS from a serial GSM Modem.");
		LOGGER.info("");

		LOGGER.info("Reading gateway's configuration properties...");

		final String configurationFilePath = "/home/doza/gsm-shield.properties"; //System.getProperty("cfg.file.path");

		final Properties properties = FileUtils.loadPropertiesOfFile(configurationFilePath);

		IDENTIFIER = properties.getProperty("gateway.identifier");

		SERIAL_PORT = properties.getProperty("gateway.serial.port");

		SERIAL_PORT_RATE = Integer.valueOf(properties.getProperty("gateway.serial.port.baudrate"));

		GATEWAY_MANUFACTURER = properties.getProperty("gateway.manufacturer");

		GATEWAY_MODEL = properties.getProperty("gateway.model");

		IS_INBOUND = Boolean.valueOf(properties.getProperty("gateway.inbound"));

		IS_OUTBOUND = Boolean.valueOf(properties.getProperty("gateway.outbound"));

		GATEWAY_SMSC_NUMBER = properties.getProperty("gateway.smsc.number");

		GETEWAY_SIM_PIN = properties.getProperty("gateway.sim.pin");

		GETEWAY_SIM_PIN_2 = properties.getProperty("gateway.sim.pin2");

		PROTOCOL = properties.getProperty("gateway.protocl");

		recipients = properties.getProperty("gateway.outbound.recipients").split("|");

		LOGGER.info("Configuration properties was readed successfully.");
		LOGGER.info("");

	}

	public Gateway() throws TimeoutException, GatewayException, IOException, InterruptedException{

		super(IDENTIFIER, SERIAL_PORT, SERIAL_PORT_RATE, GATEWAY_MANUFACTURER, GATEWAY_MODEL);
		super.setInbound(IS_INBOUND);
		super.setOutbound(IS_OUTBOUND);
		super.setSimPin(GETEWAY_SIM_PIN);

		if(!GETEWAY_SIM_PIN_2.isEmpty()){
			super.setSimPin2(GETEWAY_SIM_PIN_2);
		}

		if(!GATEWAY_SMSC_NUMBER.isEmpty()){
			super.setSmscNumber(GATEWAY_SMSC_NUMBER);
		}

		if(Protocols.PDU.equals(PROTOCOL)){
			super.setProtocol(Protocols.PDU);
		}else if(Protocols.TEXT.equals(PROTOCOL)){
			super.setProtocol(Protocols.TEXT);
		}

		//		LOGGER.info("");
		//		LOGGER.info(Library.getLibraryDescription());
		//		LOGGER.info("Version: " + Library.getLibraryVersion());
		//		LOGGER.info("");
		//		LOGGER.info("Modem Information:");
		//		LOGGER.info("  Manufacturer: " + super.getManufacturer());
		//		LOGGER.info("  Serial No: " + super.getSerialNo());
		//		LOGGER.info("  SIM IMSI: " + super.getImsi());
		//		LOGGER.info("  Signal Level: " + super.getSignalLevel() + " dBm");
		//		LOGGER.info("  Battery Level: " + super.getBatteryLevel() + "%");
		//		LOGGER.info("");

	}


	public static String[] getRecipients() {
		return recipients;
	}

	public static Sender getSender() {
		return sender;
	}

	public static Receiver getReceiver() {
		return receiver;
	}

}
