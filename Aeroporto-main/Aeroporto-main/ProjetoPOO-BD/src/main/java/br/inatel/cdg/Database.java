package br.inatel.cdg;

import java.sql.*;
import java.util.ArrayList;
public class Database {
    Connection connection;
    Statement statement;
    ResultSet result;
    PreparedStatement pst;

    static final String user = "root";
    static final String password = "@Caio100cc";
    static final String database = "mydb";

    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
    private boolean check = false;

    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    public boolean insertAviao(Aviao h) {
        connect();
        String sql = "INSERT INTO aviao(nomeAviao,qntAssentos) VALUES(?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, h.getNome());
            pst.setInt(2, h.getQtdAssentos());
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean insertPessoa(Pessoa p) {
        connect();
        String sql = "INSERT INTO pessoa(nomePessoa, cpf, ocupacao, idade) VALUES(?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getCpf());
            pst.setString(3, p.getOcupacao());
            pst.setInt(4, p.getIdade());
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean insertPAviao(int idPessoa, int idAviao) {
        connect();
        String sql = "INSERT INTO aviao_has_pessoa(aviao_idAviao,pessoa_idPessoa) VALUES(?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idPessoa);
            pst.setInt(2, idAviao);
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Pesquisa os Avioes disponiveis
    public ArrayList<Aviao> researchAviao() {
        connect();
        ArrayList<Aviao> aviao = new ArrayList<>();
        String sql = "SELECT * FROM aviao";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                Aviao h = new Aviao();
                h.idAviao = result.getInt("idAviao");
                System.out.println("ID: " + h.idAviao);
                System.out.println("Nome Aviao: " + result.getString("nomeAviao"));
                System.out.println("Assentos: " + result.getInt("qntAssentos"));
                aviao.add(h);
            }
        } catch (SQLException e) {
            System.out.println(" Erro de operação: " + e.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return aviao;
    }

    //Pesquisa os passageiros disponiveis
    public ArrayList<Pessoa> researchPassageiro() {
        connect();
        ArrayList<Pessoa> pessoa = new ArrayList<>();
        String sql = "SELECT * FROM pessoa WHERE ocupacao = 'Passageiro'";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                Passageiro h = new Passageiro(result.getString("nomePessoa"), result.getString("cpf"),result.getString("ocupacao"),result.getInt("idade"));
                h.idPessoa = result.getInt("idPessoa");
                System.out.println("ID: " + h.idPessoa);
                System.out.println("Nome: " + result.getString("nomePessoa"));
                pessoa.add(h);
            }
        } catch (SQLException e) {
            System.out.println(" Erro de operação: " + e.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return pessoa;
    }

    //Pesquisar pessoas presentes em certo aviao
    public ArrayList<Pessoa> researchListaAviao() {
        connect();
        ArrayList<Pessoa> pessoa = new ArrayList<>();
        String sql = "SELECT aviao.nomeAviao,passageiro.* FROM aviao_has_pessoa, aviao as aviao, pessoa as passageiro WHERE aviao_has_pessoa.pessoa_idPessoa = idPessoa and aviao_has_pessoa.aviao_idAviao = idAviao";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                Passageiro hos = new Passageiro(result.getString("nomePessoa"),result.getString("cpf"),result.getString("ocupacao"),result.getInt("idade"));
                Aviao aviao = new Aviao();
                System.out.println("Nome: " + result.getString("nomePessoa") + " esta no Aviao " + result.getString("nomeAviao") + " como " + result.getString("ocupacao"));
                pessoa.add(hos);
            }
        } catch (SQLException e) {
            System.out.println(" Erro de operação: " + e.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return pessoa;
    }

    //Atualizar dados do passageiro
    public boolean updatePassageiro(int id, String nome, String cpf, int idade){
        connect();
        String sql = "UPDATE pessoa SET nomePessoa = ?, cpf = ?, idade = ? WHERE id = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,nome);
            pst.setString(2,cpf);
            pst.setInt(3,idade);
            pst.setInt(4,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operacao: " + e.getMessage());
            check = false;
        }finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexao: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean deletePessoa(int id){
        connect();
        String sql = "DELETE FROM pessoa WHERE idPessoa = ?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operacao: " + e.getMessage());
            check = false;
        }finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexao: " + e.getMessage());
            }
        }
        return check;
    }
}
