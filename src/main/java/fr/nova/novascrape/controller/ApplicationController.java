package fr.nova.novascrape.controller;

import fr.nova.novascrape.themes.DarkMode;
import fr.nova.novascrape.view.LogoContainer;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.utils.ToggleButtonsUtil;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoader;
import io.github.palexdev.materialfx.utils.others.loader.MFXLoaderBean;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static fr.nova.novascrape.NovaScrapeUtils.loadURL;

public class ApplicationController implements Initializable {
    private final ToggleGroup toggleGroup;
    private final Stage stage;

    @FXML
    private StackPane logoContainer;

    @FXML
    private StackPane contentPane;

    @FXML
    private VBox navbar;

    @FXML
    private MFXFontIcon darkModeIcon;

    @FXML
    private MFXCircleToggleNode darkMode;

    public ApplicationController(Stage stage) {
        this.stage = stage;
        this.toggleGroup = new ToggleGroup();
        ToggleButtonsUtil.addAlwaysOneSelectedSupport(toggleGroup);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new LogoContainer(logoContainer, "images/nova-scrape-logo.png").setLogoContainer();

        darkMode.selectedProperty().addListener((obs, oldVal, newVal) -> {
            switchDarkMode(newVal);
        });

        initializeLoader();
    }

    public void initializeLoader() {
        MFXLoader loader = new MFXLoader();
        loader.addView(MFXLoaderBean.of("RESTAURANTS", loadURL("fxml/Restaurant.fxml")).setBeanToNodeMapper(() -> createToggle("fas-utensils", "Restaurants")).setDefaultRoot(true).setControllerFactory(c -> new RestaurantController(stage)).get());
        loader.addView(MFXLoaderBean.of("SALON DE COIFFURE", loadURL("fxml/HairSalon.fxml")).setBeanToNodeMapper(() -> createToggle("fas-scissors", "Salon de coiffure")).setControllerFactory(c -> new HairSalonController(stage)).get());
        loader.addView(MFXLoaderBean.of("SUPERMARCHE", loadURL("fxml/Supermarket.fxml")).setBeanToNodeMapper(() -> createToggle("fas-basket-shopping", "Supermarchés")).setControllerFactory(c -> new SupermarketController(stage)).get());
        loader.setOnLoadedAction(beans -> {
            List<ToggleButton> nodes = beans.stream()
                    .map(bean -> {
                        ToggleButton toggle = (ToggleButton) bean.getBeanToNodeMapper().get();
                        toggle.setOnAction(event -> contentPane.getChildren().setAll(bean.getRoot()));
                        if (bean.isDefaultView()) {
                            contentPane.getChildren().setAll(bean.getRoot());
                            toggle.setSelected(true);
                        }
                        return toggle;
                    })
                    .toList();
            navbar.getChildren().setAll(nodes);
        });
        loader.start();
    }

    public void switchDarkMode(boolean enabled) {

        if (enabled) {
            darkModeIcon.setDescription("fas-moon");
            darkMode.lookup(".mfx-circle-toggle-node")
                    .setStyle("-mfx-selected-color: #454545;");
            darkModeIcon.setColor(Color.WHITE);
            stage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/fr/nova/novascrape/css/themes/Dark.css")).toExternalForm());
        } else {
            darkModeIcon.setDescription("fas-sun");
            darkMode.lookup(".mfx-circle-toggle-node")
                    .setStyle("-mfx-selected-color: #fff;");
            darkModeIcon.setColor(Color.valueOf("#454545"));
            stage.getScene().getStylesheets().removeIf(s -> s.contains("Dark.css"));
        }
        DarkMode.getInstance().setDarkModeEnabled(enabled);
    }

    private ToggleButton createToggle(String icon, String text) {
        return createToggle(icon, text, 0);
    }

    private ToggleButton createToggle(String icon, String text, double rotate) {
        MFXIconWrapper wrapper = new MFXIconWrapper(icon, 24, 32);
        MFXRectangleToggleNode toggleNode = new MFXRectangleToggleNode(text, wrapper);
        toggleNode.setAlignment(Pos.CENTER_LEFT);
        toggleNode.setMaxWidth(Double.MAX_VALUE);
        toggleNode.setToggleGroup(toggleGroup);
        if (rotate != 0) wrapper.getIcon().setRotate(rotate);
        return toggleNode;
    }
}
