package com.example.studytime;

import java.util.List;

public interface IAllSalleLoadListener {
    void onAllSalleloadSuccess(List<String> areaNameList);
    void onAllSalleloadFailed(String message);
}
