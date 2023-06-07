package br.com.senac.avaliacaoJava.Controller;

import br.com.senac.avaliacaoJava.Model.Cliente;
import br.com.senac.avaliacaoJava.Model.Enderecos;
import br.com.senac.avaliacaoJava.ServiceCliente.ServiceCliente;
import br.com.senac.avaliacaoJava.ServiceEnderecos.ServiceEnderecos;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("/main.fxml")
public class CadastroController {

    @FXML
    private TextField nomeCliente;

    @FXML
    private TextField docCliente;

    @FXML
    private TextField rgCliente;

    @FXML
    private TextField emailCliente;

    @FXML
    private TextField telCliente;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableColumn<Cliente, String> docCadastroCliente;

    @FXML
    private TableColumn<Cliente, String> nomeCadastroCliente;

    @FXML
    private TableColumn<Cliente, String> rgCadastroCliente;

    @FXML
    private TableColumn<Cliente, String> emailCadastroCliente;

    @FXML
    private TableColumn<Cliente, String> telCadastroCliente;

    @FXML
    private TextField cepCliente;

    @FXML
    private TextField ruaCliente;

    @FXML
    private TextField numCasaCliente;

    @FXML
    private TextField bairroCliente;

    @FXML
    private TextField cidadeCliente;

    @FXML
    private TextField estadoCliente;

    @FXML
    private TableView<Enderecos> tabelaEnderecos;

    @FXML
    private TableColumn<Enderecos, Integer> idCliente;

    @FXML
    private TableColumn<Enderecos, String> cepEndCliente;
    @FXML
    private TableColumn<Enderecos, String> ruaEndCliente;

    @FXML
    private TableColumn<Enderecos, String> numEndCliente;

    @FXML
    private TableColumn<Enderecos, String> bairroEndCliente;

    @FXML
    private TableColumn<Enderecos, String> cidadeEndCliente;

    @FXML
    private TableColumn<Enderecos, String> estadoEndCliente;

    private int index = -1;

    @FXML
    public void initialize() {
        docCadastroCliente.setCellValueFactory(new PropertyValueFactory<>("documento"));
        nomeCadastroCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        rgCadastroCliente.setCellValueFactory(new PropertyValueFactory<>("rg"));
        emailCadastroCliente.setCellValueFactory(new PropertyValueFactory<>("email"));
        telCadastroCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        cepEndCliente.setCellValueFactory(new PropertyValueFactory<>("cep"));
        ruaEndCliente.setCellValueFactory(new PropertyValueFactory<>("rua"));
        numEndCliente.setCellValueFactory(new PropertyValueFactory<>("numero"));
        bairroEndCliente.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        cidadeEndCliente.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        estadoEndCliente.setCellValueFactory(new PropertyValueFactory<>("estado"));

        this.carregarTabelaClientes();
        //this.carregarEnderecos();

        tabelaClientes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Cliente cli = tabelaClientes.getSelectionModel().getSelectedItem();
                    nomeCliente.setText(cli.getNomeCliente());
                    docCliente.setText(cli.getDocCliente());
                    rgCliente.setText(cli.getRgCliente());
                    emailCliente.setText(cli.getEmailCliente());
                    telCliente.setText(cli.getTelCliente());

                    index = cli.getIdCliente();
                }
            }
        });

        tabelaEnderecos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Enderecos end = tabelaEnderecos.getSelectionModel().getSelectedItem();
                    cepCliente.setText(end.getCepCliente());
                    ruaCliente.setText(end.getRuaCliente());
                    numCasaCliente.setText(end.getNumCasaCliente());
                    bairroEndCliente.setText(end.getNumCasaCliente());
                    cidadeCliente.setText(end.getCidadeCliente());
                    estadoCliente.setText(end.getEstadoCliente());


                    index = end.getIdEndereco();
                }
            }
        });

    }

    public void salvarCli(){

        /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Campos Nome: " + nomeCliente.getText() + " Documento: " + docCliente.getText() + " são obrigatórios!");
        alert.show(); */

        Cliente cli = new Cliente();
        cli.setNomeCliente(nomeCliente.getText());
        cli.setDocCliente(docCliente.getText());
        cli.setRgCliente(rgCliente.getText());
        cli.setEmailCliente(emailCliente.getText());
        cli.setTelCliente(telCliente.getText());

        // atualiza item - resetar index
        if(index > -1){
        //    tabelaClientes.getItems().set(index, cli);

        ServiceCliente.atualizarCliente(index, cli);


            index = -1;
        }else {
            // inclui novo registro
        //    tabelaClientes.getItems().add(cli);

            if(!cli.getDocCliente().matches("[0-9]*")) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro");
                alerta.setHeaderText("Documento invalido");
                alerta.show();

            }else if(ServiceCliente.buscarClienteByDocumento(cli.getDocCliente())) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Alerta!");
                alerta.setHeaderText("Documento: " + docCliente.getText() + " já existe na base!");
                alerta.show();

            }else {
                ServiceCliente.inserirCliente(cli);

            }
        }


        this.carregarTabelaClientes();

        this.limparCampos();

    }

    public void salvarEnd(){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Campos CEP: " + cepEndCliente.getText() + " Número: " + numEndCliente.getText() + " Bairro: " + bairroEndCliente + " Cidade: " + cidadeEndCliente.getText() + " e Estado: " + estadoEndCliente.getText() + " são obrigatórios!");
        alert.show();

        Enderecos end = new Enderecos();
        end.setCepCliente(cepEndCliente.getText());
        end.setRuaCliente(ruaEndCliente.getText());
        end.setNumCasaCliente(numEndCliente.getText());
        end.setBairroCliente(bairroEndCliente.getText());
        end.setCidadeCliente(cidadeEndCliente.getText());
        end.setEstadoCliente(estadoEndCliente.getText());

        // atualiza item - resetar index
        if(index > -1){
            //    tabelaClientes.getItems().set(index, cli);

            ServiceEnderecos.atualizarEnderecos(index, end);


            index = -1;
        }else {
            // inclui novo registro
            //    tabelaClientes.getItems().add(cli);

            if(!end.getCepCliente().matches("[0-9]*")) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Documento invalido");
                alert.show();

            }else if(ServiceEnderecos.buscarEnderecoByCep(end.getCepCliente())) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Alerta!");
                alerta.setHeaderText("Documento: " + cepEndCliente.getText() + " já existe na base!");
                alerta.show();

            }else {
                ServiceEnderecos.inserirEnderecos(end);

            }
        }


        this.carregarTabelaEnderecos();

        this.limparCampos();

    }

    

    public void excluirCli(){
        if(index > -1){
            //tabelaClientes.getItems().remove(index);
            ServiceCliente.deletarCliente(index);
            this.carregarTabelaClientes();

            index = -1;
            this.limparCampos();
        }
    }

    public void excluirEnd(){
        if(index > -1){
            //tabelaClientes.getItems().remove(index);
            ServiceEnderecos.deletarEnderecos(index);
            this.carregarTabelaEnderecos();
            index = -1;
            this.limparCampos();
        }
    }


    public void limparCampos(){
        nomeCliente.setText("");
        docCliente.setText("");
        rgCliente.setText("");
        emailCliente.setText("");
        telCliente.setText("");
        cepCliente.setText("");
        ruaCliente.setText("");
        numCasaCliente.setText("");
        bairroCliente.setText("");
        cidadeCliente.setText("");
        estadoCliente.setText("");


    }

    public void carregarTabelaClientes(){

        tabelaClientes.getItems().remove(0, tabelaClientes.getItems().size());

        List<Cliente> cliList = ServiceCliente.carregarClientes();

        tabelaClientes.getItems().addAll(cliList);
    }

    public void carregarTabelaEnderecos(){

        tabelaEnderecos.getItems().remove(0, tabelaEnderecos.getItems().size());

        List<Enderecos> endList = ServiceEnderecos.carregarEnderecos();

        tabelaEnderecos.getItems().addAll(endList);
    }

}

