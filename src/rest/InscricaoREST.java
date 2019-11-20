/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import entidade.Inscricao;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author morgana
 */
public class InscricaoREST {

    public ArrayList carregarInscritos(int evento) throws Exception {
        String url = "http://localhost/api_eventos/api/inscricao/read_event.php?evento=" + evento;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        String inputLine;
        BufferedReader in;
        StringBuffer response;

        try {
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            response = new StringBuffer();
        } catch (Exception e) {
            return null;
        }

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Gson g = new Gson();
        Type collectionType = new TypeToken<ArrayList<Inscricao>>() {
        }.getType();
        ArrayList<Inscricao> inscritos = g.fromJson(response.toString(), collectionType);

        return inscritos;
    }

    public boolean cadastrarInscricao(String inscr) throws Exception {
        String url = "http://localhost/api_eventos/api/inscricao/create.php";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        String urlParameters = inscr;
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        con.setUseCaches(false);

        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.write(postData);
        }

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        
        return responseCode == 200;
    }

}
