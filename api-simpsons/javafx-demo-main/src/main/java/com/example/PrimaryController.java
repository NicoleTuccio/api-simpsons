package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {

    @FXML Pagination pagination;

    private int pagina = 1;
    

    public FlowPane carregar(){
        try{
            var url = new URL("https://thesimpsonsquoteapi.glitch.me/quotes?count=15&character=ho&page=" + pagina);
            var conexao = url.openConnection();
            conexao.connect();
            var is = conexao.getInputStream();
            var reader = new BufferedReader(new InputStreamReader(is));
            var json = reader.readLine();

            var lista = jsonParaLista(json);
            
            return mostrarPersonagens(lista);
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private FlowPane mostrarPersonagens(List<Personagem>lista) {
        var flow = new FlowPane();
        flow.setHgap(20);
        flow.setVgap(20);
        lista.forEach(p -> {
            var image = new ImageView (new Image(p.getImage()));
            image.setFitHeight(100); //tamanho da imagem
            image.setFitWidth(100);
            var labelName = new Label(p.getCharacter());
            var labelQuote = new Label(p.getQuote());
            flow.getChildren().add(new VBox(image, labelName, labelQuote));
        });
        return flow;
    }

    private List<Personagem> jsonParaLista(String json) throws JsonMappingException, JsonProcessingException {
        var mapper = new ObjectMapper();
        var results = mapper.readTree(json);
        List<Personagem> lista = new ArrayList<>();

        results.forEach(personagem -> {
            try {
                var p = mapper.readValue(personagem.toString(), Personagem.class);
                lista.add(p);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return lista;
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pagination.setPageFactory(pag -> {
            pagina = pag + 1;
            return carregar();
        });
    }
    
    }

