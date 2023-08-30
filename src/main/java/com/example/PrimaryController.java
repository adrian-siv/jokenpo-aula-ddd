package com.example;

import java.util.ArrayList;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class PrimaryController {
    // textField e TextArea são objetos/classes
    @FXML
    RadioButton rdbPedra;
    @FXML
    RadioButton rdbPapel;
    @FXML
    RadioButton rdbTesoura;
    @FXML
    Button buttonJogar;

    String opcaoSelecionada;

    Random numeroAleatorio = new Random();

    public void capturarOpcao() {
        if (rdbPedra.isSelected()) {
            opcaoSelecionada = "pedra";
        } else if (rdbPapel.isSelected()) {
            opcaoSelecionada = "papel";
        } else if (rdbTesoura.isSelected()) {
            opcaoSelecionada = "tesoura";
        }
    }

    public void definirJogadaBOT() {
        String opcaoJogadaBOT = "";
        Integer numeroSorteado = numeroAleatorio.nextInt(3);

        if (numeroSorteado == 0) {
            opcaoJogadaBOT = "pedra";
        } else if (numeroSorteado == 1) {
            opcaoJogadaBOT = "papel";
        } else if (numeroSorteado == 2) {
            opcaoJogadaBOT = "tesoura";
        }

        System.out.println(numeroAleatorio);
        System.out.println(opcaoJogadaBOT);
    }
}
