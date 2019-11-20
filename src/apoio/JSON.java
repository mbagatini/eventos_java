/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import entidade.Inscricao;
import java.io.FileReader;
import java.io.FileWriter;
import rest.InscricaoREST;

/**
 *
 * @author morgana
 */
public class JSON {

    public void gravarInscrito(Inscricao ins) throws Exception{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // construct Type that tells Gson about the generic type
        Type dtoListType = new TypeToken<ArrayList<Inscricao>>() {}.getType();
        FileReader fr = new FileReader("sync.json");
        ArrayList<Inscricao> dtos = gson.fromJson(fr, dtoListType);
        fr.close();
        
        // If it was an empty one create initial list
        if (null == dtos) {
            dtos = new ArrayList<>();
        }
        
        // Add new item to the list
        dtos.add(ins);
        
        // No append replace the whole file
        FileWriter fw = new FileWriter("sync.json");
        gson.toJson(dtos, fw);
        fw.close();
    }
    
    public void sincronizarInscricoes() throws Exception{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // construct Type that tells Gson about the generic type
        Type dtoListType = new TypeToken<ArrayList<Inscricao>>() {}.getType();
        FileReader fr = new FileReader("sync.json");
        ArrayList<Inscricao> dtos = gson.fromJson(fr, dtoListType);
        fr.close();
        
        // If it was an empty one create initial list
        if (null == dtos) {
            return;
        }
        
        ArrayList<Inscricao> atualizado = new ArrayList();
        
        for (Inscricao ins : dtos) {
            InscricaoREST rest = new InscricaoREST();
            if (rest.cadastrarInscricao(gson.toJson(ins))) {
            } else {
                atualizado.add(ins);
            }
        }
        
        reescreverSincronizacao(atualizado);
        
        apoio.Mensagem.mostraInformacao("Sincronização", "Os dados foram sincronizados");
    }
    
    private void reescreverSincronizacao(ArrayList<Inscricao> atualizado)throws Exception{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        // construct Type that tells Gson about the generic type
        Type dtoListType = new TypeToken<ArrayList<Inscricao>>() {}.getType();
        
        // No append replace the whole file
        FileWriter fw = new FileWriter("sync.json");
        gson.toJson(atualizado, fw);
        fw.close();
    }

}
