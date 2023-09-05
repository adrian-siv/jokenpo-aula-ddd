package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ResultadoDao {

    private final String HOST = "auth-db719.hstgr.io";
    private final String PORT = "1521";
    private final String USER = "rm97784";
    private final String PASS = "081100";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public void inserir(Integer numeroPartida, Integer ptsJogador, Integer ptsBot, String vencedor)
            throws SQLException {
        var con = DriverManager.getConnection(URL, USER, PASS);

        var sql = "INSERT INTO resultados (id_partida, pts_jogador, pts_bot, vencedor) VALUES (?, ?, ?, ?)";
        var instrucao = con.prepareStatement(sql);
        instrucao = con.prepareStatement(sql);
        instrucao.setInt(1, numeroPartida);
        instrucao.setInt(2, ptsJogador);
        instrucao.setInt(3, ptsBot);
        instrucao.setString(4, vencedor);

        instrucao.executeUpdate();

        con.close();
    }

}
