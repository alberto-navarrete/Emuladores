package com.stefanini.emuladoresFG;

import java.time.LocalDate;
import java.util.Random;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class EmuladorJMSProducer {

	private static String url = "tcp://localhost:61616";
	private static String queueName = "pruebas_queue";
    
	public static void main(String[] args) {
		try {
			System.out.println("Inicia");
			// create connection factory
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", url);
			// create connection
			Connection connection;

			connection = connectionFactory.createConnection();

			connection.start();
			// create session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// create destination
			Destination destination = session.createQueue(queueName);
			// create producer
			MessageProducer producer = session.createProducer(destination);
			// create message
			TextMessage message = null;
			
			for(int i=0;i<101;i++) {
				message = session.createTextMessage(EmuladorJMSProducer.generateShipmentAdvicesXML());
				
				// send message
				producer.send(message);
			}
			
			// clean up resources
			session.close();
			connection.close();
			System.out.println("DONE!");

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	private static final Random random = new Random();

    public static String generateShipmentAdvicesXML() {
        // Generar valores aleatorios o dinámicos
        String orderDate = LocalDate.now().toString();
        String reqDeliveryDate = LocalDate.now().plusDays(2).toString();
        String sanNo = "1090" + (random.nextInt(9000000) + 1000000) + "_1";
        String salesOrderNo = "1090" + (random.nextInt(9000000) + 1000000);
        String itemID = String.valueOf(random.nextInt(1000000) + 1000000);
        String dayPhone = "123456" + (random.nextInt(9000) + 1000);

        // Construir el XML
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<ShipmentAdvices ReceivingNode=\"\" ShipNode=\"SU9501\" TotalShipmentAdvices=\"1\">")
                .append("<ShipmentAdvice ")
                .append("CarrierServiceCode=\"Estandar\" ")
                .append("EnterpriseCode=\"FARM_GUAD\" ")
                .append("OrderDate=\"").append(orderDate).append("\" ")
                .append("ReleaseNo=\"1\" ")
                .append("ReqDeliveryDate=\"").append(reqDeliveryDate).append("\" ")
                .append("ReqShipDate=\"").append(orderDate).append("\" ")
                .append("SADate=\"").append(orderDate).append("\" ")
                .append("SANo=\"").append(sanNo).append("\" ")
                .append("SCAC=\"9999\" ")
                .append("SalesOrderNo=\"").append(salesOrderNo).append("\" ")
                .append("SellerOrganizationCode=\"FARM_GUAD\">")

                .append("<ShipNode AddressLine1=\"Av España\" AddressLine2=\"1924\" City=\"Guadalajara\" Country=\"MX\" State=\"Jalisco\" ZipCode=\"44190\"/>")

                .append("<SALines TotalSALines=\"1\">")
                .append("<SALine CarrierServiceCode=\"Estandar\" FulfillmentType=\"FG_DELIVERY\" LineType=\"ABARROTES\" ")
                .append("OrderedQty=\"1.00\" OriginalOrderedQty=\"1.00\" PrimeLineNo=\"1\" SCAC=\"9999\" SubLineNo=\"1\">")

                .append("<Item ItemDesc=\"Chiles Jalapeños en Escabeche La Costeña para Nachos, 220 gr.\" ")
                .append("ItemID=\"").append(itemID).append("\" ")
                .append("ItemShortDesc=\"Chiles Jalapeños en Escabeche La Costeña para Nachos, 220 gr.\" ")
                .append("ProductLine=\"ABARROTES\" UnitOfMeasure=\"EACH\"/>")

                .append("<PackListPriceInfo Charges=\"0.00\" ExtendedPrice=\"14.50\" LineTotal=\"14.50\" ListPrice=\"14.50\" RetailPrice=\"14.50\" UnitPrice=\"14.50\">")
                .append("<LineTaxes>")
                .append("<TaxSummary>")
                .append("<TaxSummaryDetail Tax=\"0.00\" TaxName=\"Sales Tax\"/>")
                .append("<TaxSummaryDetail Tax=\"0.00\" TaxName=\"Shipping Tax\"/>")
                .append("</TaxSummary>")
                .append("</LineTaxes>")
                .append("</PackListPriceInfo>")

                .append("<Extn ExtnDateDeliveryApproximate=\"").append(orderDate).append("T11:14-0600\" ExtnTimeDelivery=\"120 mins.\"/>")
                .append("</SALine>")
                .append("</SALines>")

                .append("<ShipTo AddressLine1=\"Mar Báltico\" AddressLine2=\"2242\" City=\"Guadalajara\" Country=\"México\" ")
                .append("DayPhone=\"").append(dayPhone).append("\" EmailID=\"victor_andrade@fragua.com.mx\" ")
                .append("FirstName=\"Victor Manuel\" LastName=\"Andrade\" State=\"Jalisco\" ZipCode=\"44100\"/>")

                .append("<BillTo AddressLine1=\"Avenida Enrique Díaz de León Norte\" AddressLine2=\"261\" City=\"Guadalajara\" Country=\"México\" ")
                .append("DayPhone=\"6666666666\" EmailID=\"victor_andrade@fragua.com.mx\" ")
                .append("FirstName=\"Victor Manuel\" LastName=\"Andrade\" State=\"Jalisco\" ZipCode=\"44100\"/>")

                .append("<PackListPriceInfo TotalAmount=\"34.50\" TotalCharges=\"20.00\">")
                .append("<TotalSummary>")
                .append("<ChargeSummary>")
                .append("<ChargeSummaryDetail ChargeAmount=\"20.00\" ChargeCategory=\"Shipping\" ChargeName=\"Shipping Charge\"/>")
                .append("</ChargeSummary>")
                .append("<TaxSummary>")
                .append("<TaxSummaryDetail Tax=\"0.00\" TaxName=\"Sales Tax\"/>")
                .append("<TaxSummaryDetail Tax=\"0.00\" TaxName=\"Shipping Tax\"/>")
                .append("</TaxSummary>")
                .append("</TotalSummary>")
                .append("</PackListPriceInfo>")
                .append("</ShipmentAdvice>")
                .append("<Extn ExtnQuoteId=\"75460\" ExtnServiceId=\"49592\"/>")
                .append("</ShipmentAdvices>");

        return xmlBuilder.toString();
    }
}