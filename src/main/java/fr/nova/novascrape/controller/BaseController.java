package fr.nova.novascrape.controller;

import fr.nova.novascrape.model.base.BaseEntity;
import fr.nova.novascrape.model.details.BaseEntityDetail;
import fr.nova.novascrape.service.WebScrapingDetail;
import fr.nova.novascrape.service.WebScrapingService;
import fr.nova.novascrape.view.CardView;
import fr.nova.novascrape.view.DetailView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;

public abstract class BaseController<T extends BaseEntity, D extends BaseEntityDetail> implements Initializable {
    protected final WebScrapingService webScrapingService = new WebScrapingService();
    protected final WebScrapingDetail webscrapingDetail = new WebScrapingDetail();
    protected MFXGenericDialog dialogContent;
    protected MFXStageDialog dialog;

    @FXML
    protected FlowPane cardContainer;

    private final Stage stage;

    public BaseController(Stage stage) {
        this.stage = stage;
        initDialog();
    }

    private void initDialog() {
        Platform.runLater(() -> {
            dialogContent = MFXGenericDialogBuilder.build()
                    .setContentText("Loading...")
                    .makeScrollable(true)
                    .get();

            dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initOwner(stage)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setTitle("Dialogs Preview")
                    .setOwnerNode(cardContainer)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            dialogContent.addActions(Map.entry(new MFXButton("Fermer"), event -> dialog.close()));
            dialogContent.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/fr/nova/novascrape/css/themes/Dark.css")).toExternalForm()
            );
            dialogContent.setMaxSize(600, 500);
        });
    }

    protected void displayItems(List<T> items, Function<T, CardView<T>> cardFactory) {
        items.forEach(item -> {
            CardView<T> cardView = cardFactory.apply(item);
            VBox card = cardView.getCard();
            cardView.getCardButton().setOnAction(event -> openInfo(event, item));
            cardContainer.getChildren().add(card);
        });
    }

    protected void openInfo(ActionEvent event, T entity) {
        DetailView<D> view = createDetailView();
        view.applyDarkTheme();
        view.showLoading(entity.getNom());

        Task<D> task = new Task<>() {
            @Override
            protected D call() {
                return fetchDetails(entity);
            }
        };

        task.setOnSucceeded(e -> view.showDetails(task.getValue()));
        task.setOnFailed(e -> {
            view.showError("Impossible de charger les informations.");
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }

    protected abstract List<T> getItems();

    protected abstract Function<T, CardView<T>> getCardFactory();

    protected abstract DetailView<D> createDetailView();

    protected abstract D fetchDetails(T entity);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayItems(getItems(), getCardFactory());
    }
}
