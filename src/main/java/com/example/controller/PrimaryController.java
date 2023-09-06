package com.example.controller;

import java.sql.SQLException;
import java.util.Random;
import com.example.data.ResultadoDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

public class PrimaryController {
    @FXML
    RadioButton rdbPedra;
    @FXML
    RadioButton rdbPapel;
    @FXML
    RadioButton rdbTesoura;
    @FXML
    Button buttonJogar;

    String opcaoSelecionada;
    String opcaoJogadaBOT = "";

    Integer ptsJogador = 0;
    Integer ptsBot = 0;

    Random numeroAleatorio = new Random();

    public String capturarOpcao() {
        if (rdbPedra.isSelected()) {
            opcaoSelecionada = "pedra";
        } else if (rdbPapel.isSelected()) {
            opcaoSelecionada = "papel";
        } else if (rdbTesoura.isSelected()) {
            opcaoSelecionada = "tesoura";
        }

        return opcaoSelecionada;
    }

    public String definirJogadaBOT() {
        Integer numeroSorteado = numeroAleatorio.nextInt(3);

        if (numeroSorteado == 0) {
            opcaoJogadaBOT = "pedra";
        } else if (numeroSorteado == 1) {
            opcaoJogadaBOT = "papel";
        } else if (numeroSorteado == 2) {
            opcaoJogadaBOT = "tesoura";
        }

        return opcaoJogadaBOT;
    }

    public String determinarVencedor() {
        if (ptsJogador == 3) {
            return "Jogador";
        } else if (ptsBot == 3) {
            return "Bot";
        }
        return "";
    }

    public void terminarPartida() {
        if (ptsJogador == 3 || ptsBot == 3) {
            ptsJogador = 0;
            ptsBot = 0;
        }
    }

    public void jogar() {
        capturarOpcao();
        definirJogadaBOT();

        // Caso de empate
        if (opcaoSelecionada.equals(opcaoJogadaBOT)) {
            Alert alertaEmpate = new Alert(AlertType.INFORMATION, "Empate!");
            alertaEmpate.setHeaderText("Resultado");
            alertaEmpate.show();

        // Caso de vitória do Jogador
        } else if ((opcaoSelecionada.equals("pedra") && opcaoJogadaBOT.equals("tesoura"))
                || (opcaoSelecionada.equals("papel") && opcaoJogadaBOT.equals("pedra"))
                || (opcaoSelecionada.equals("tesoura") && opcaoJogadaBOT.equals("papel"))) {
            ptsJogador++;
            if (ptsJogador < 3) {
                Alert alertaVitoria = new Alert(AlertType.INFORMATION,
                        "Você venceu!\nPlacar atualizado:\nJogador " + ptsJogador + "x" + ptsBot + " Bot");
                alertaVitoria.setHeaderText("Resultado");
                alertaVitoria.show();
            } else {
                declararResultado("Jogador");
            }

        // Caso de vitória do Bot
        } else {
            ptsBot++;
            if (ptsBot < 3) {
                Alert alertaVitoria = new Alert(AlertType.INFORMATION,
                        "Você perdeu!\nPlacar atualizado:\nJogador " + ptsJogador + "x" + ptsBot + " Bot");
                alertaVitoria.setHeaderText("Resultado");
                alertaVitoria.show();
            } else {
                declararResultado("Bot");
            }
        }

        terminarPartida();
    }

    private void declararResultado(String vencedor) {
        String perdeuOuVenceu = "";
        if (vencedor == "Jogador") {
            perdeuOuVenceu = "venceu";
        } else {
            perdeuOuVenceu = "perdeu";
        }
        Alert alertaVitoria = new Alert(AlertType.INFORMATION,
                "Você " + perdeuOuVenceu + " a partida!\nPlacar final:\nJogador " + ptsJogador + "x" + ptsBot
                        + " Bot\nPara outra partida melhor de 5, clique em jogar.");
        alertaVitoria.setHeaderText("FIM DE JOGO!");
        alertaVitoria.show();

        ResultadoDao resultadoDao = new ResultadoDao();
        try {
            resultadoDao.inserir(ptsJogador, ptsBot, vencedor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
