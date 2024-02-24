package iti.iti_simulation;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class TableViewHelperAdapter<T> implements TableViewHelper<T> {
    private final TableView<T> tableView;
    private final ObservableList<T> observableList;

    public TableViewHelperAdapter(TableView<T> tableView, ObservableList<T> observableList) {
        this.tableView = tableView;
        this.observableList = observableList;
    }

    @Override
    public void fillRecords(ArrayList<T> arrayListRecords) {
        observableList.setAll(arrayListRecords);
        tableView.setItems(observableList);
    }


}
