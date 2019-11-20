/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entidade.Usuario;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author morgana
 */
public class UsuarioREST {

    public Usuario validarLogin(String email, String senha) throws Exception {

        String url = "http://localhost/api_eventos/api/usuario/login.php?email=" + email + "&senha=" + senha;

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
        Usuario user = g.fromJson(response.toString(), Usuario.class);

        return user;
    }

}
