package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Controller {

    @FXML
    private TextField tfID;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAuthor;

    @FXML
    private TextField tfYear;

    @FXML
    private TextField tfPages;

    @FXML
    private TableView<Books> tvBooks;

    @FXML
    private TableColumn<Books, Integer> colID;

    @FXML
    private TableColumn<Books, String> colTitle;

    @FXML
    private TableColumn<Books, String> colAuthor;

    @FXML
    private TableColumn<Books, Integer> colYear;

    @FXML
    private TableColumn<Books, Integer> colPages;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    void clickDelete(ActionEvent event) {

    }

    @FXML
    void clickInsert(ActionEvent event) {

    }

    @FXML
    void clickUpdate(ActionEvent event) {

    }

    public Connection getConnection()
    {
        Connection connection;

        try
        {
            connection = DriverManager.getConnection("I don't know how to make a server");
            return connection;
        }
        catch (Exception exception)
        {
            System.out.println("Error: " + exception.getMessage());
            return null;
        }
    }

    public ObservableList<Books> getBooksList()
    {
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM books";
        Statement statement;
        ResultSet resultSet;

        try
        {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Books books;
            while (resultSet.next())
            {
                books = new Books(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getInt("year"), resultSet.getInt("pages"));
                bookList.add(books);
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return bookList;
    }

    public void showBooks()
    {
        ObservableList<Books> list = getBooksList();

        colID.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Books, String >("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        colYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        colPages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("pages"));
    }

}
