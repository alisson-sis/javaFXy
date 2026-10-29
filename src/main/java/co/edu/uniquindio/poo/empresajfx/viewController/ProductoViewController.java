package co.edu.uniquindio.poo.empresajfx.viewController;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.empresajfx.App;

import co.edu.uniquindio.poo.empresajfx.controller.ProductoController;

import co.edu.uniquindio.poo.empresajfx.model.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProductoViewController {
    ProductoController productoController;
    ObservableList<Producto> listProductos = FXCollections.observableArrayList();
    Producto selectedProducto;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField txtNombreProducto;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TableView<Producto> tblListProductos;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private TableColumn<Producto, String> tbcNombreProducto;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TableColumn<Producto, String> tbcCodigo;

    @FXML
    private Button btbAgregarProducto;

    @FXML
    private TableColumn<Producto, String> tbcPrecio;

    @FXML
    private TextField txtPrecio;
    private App app;

    @FXML
    void onAgregarProducto() {
        agregarProducto();
    }

    @FXML
    void onActualizarProducto() {
        actualizarProducto();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void onEliminar() {
        eliminarProducto();
    }

    @FXML
    void initialize() {
        this.app = app;
        productoController = new ProductoController(app.empresa);
        initView();
    }

    private void initView() {
        // Traer los datos del cliente a la tabla
        initDataBinding();

        // Obtiene la lista
        obtenerProductos();

        // Limpiar la tabla
        tblListProductos.getItems().clear();

        // Agregar los elementos a la tabla
        tblListProductos.setItems(listProductos);

        // Seleccionar elemento de la tabla
        listenerSelection();
    }

    private void initDataBinding() {
        tbcNombreProducto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreProducto()));
        tbcPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrecio()));
        tbcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        // Usamos SimpleObjectProperty para manejar Double y Integer correctamente
    }

    private void obtenerProductos() {
        listProductos.addAll(productoController.obtenerListaProductos());
    }

    private void listenerSelection() {
        tblListProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProducto = newSelection;
            mostrarInformacionProducto(selectedProducto);
        });
    }

    private void mostrarInformacionProducto(Producto producto) {
        if (producto != null) {
            txtNombreProducto.setText(producto.getNombreProducto());
            txtPrecio.setText(producto.getPrecio());
            txtCodigo.setText(producto.getCodigo());
        }
    }

    private void agregarProducto() {
        Producto producto = buildProducto();
        if (productoController.crearProducto(producto)) {
            listProductos.add(producto);
            limpiarCamposProducto();
        }
    }

    private Producto buildProducto() {
        Producto producto = new Producto(txtNombreProducto.getText(), txtPrecio.getText(), txtCodigo.getText());
        return producto;
    }

    private void eliminarProducto() {
        if (productoController.eliminarProducto(txtCodigo.getText())) {
            listProductos.remove(selectedProducto);
            limpiarCamposProducto();
            limpiarSeleccion();
        }
    }

    private void actualizarProducto() {

        if (selectedProducto != null &&productoController.actualizarProducto(selectedProducto.getCodigo(), buildProducto())) {

            int index = listProductos.indexOf(selectedProducto);
            if (index >= 0) {
                listProductos.set(index, buildProducto());
            }

            tblListProductos.refresh();
            limpiarSeleccion();
            limpiarCamposProducto();
        }
    }

    private void limpiarSeleccion() {
        tblListProductos.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void limpiarCamposProducto() {
        txtNombreProducto.clear();
        txtPrecio.clear();
        txtCodigo.clear();
    }

    public void setApp(App app) {
        this.app = app;
    }
}

