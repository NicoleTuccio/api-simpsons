package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Personagem {
    private String quote;
    private String character;
    private String image;
    
    @JsonIgnore
    private String characterDirection;
    
    
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }
    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getCharacterDirection() {
        return characterDirection;
    }
    public void setCharacterDirection(String characterDirection) {
        this.characterDirection = characterDirection;
    }
    
    
}
