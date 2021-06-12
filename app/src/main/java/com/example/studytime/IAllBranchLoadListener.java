package com.example.studytime;

import java.util.List;

public interface IAllBranchLoadListener {
    void onAllBranchloadSuccess(List<Salon> salleList);
    void onAllBranchloadFailed(String message);
}
