package com.realdolmen.togethair.beans;

import net.bootsfaces.utils.FacesMessages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@ManagedBean
@ViewScoped
public class PaymentBean {

    private String ccNumber;
    private String ccName;
    private String ccValidDate;
    private String ccv;

    private String targetURL = "http://localhost:9080/payment-service/api/creditcard/validation";
    private String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()

    public String validate() {
        HttpURLConnection connection = null;


        try {

            String urlParameters = String.format("cardNumber=%s&name=%s&validDate=%s&ccv=%s",
                    URLEncoder.encode(ccNumber, charset),
                    URLEncoder.encode(ccName, charset),
                    URLEncoder.encode(ccValidDate, charset),
                    URLEncoder.encode(ccv, charset));

            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            System.out.println(connection.getContent().toString());
            InputStream is = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();

            String responseMessage = response.toString();

            if (responseMessage.startsWith("false"))
                FacesMessages.error(responseMessage.substring(6));
            else if (responseMessage.startsWith("true"))
                return "end";

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessages.fatal("Payments are not available at the moment. Please try again later.");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getCcValidDate() {
        return ccValidDate;
    }

    public void setCcValidDate(String ccValidDate) {
        this.ccValidDate = ccValidDate;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }
}
