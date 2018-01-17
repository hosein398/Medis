package com.holding.medis.presenters;

import com.holding.medis.contract.CalenderContract;
import com.holding.medis.contract.LaunchContract;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.tools.UserTools;
import com.holding.medis.tools.ValidationTools;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.activities.RegisterActivity;

import java.util.ArrayList;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class CalenderPresenter implements CalenderContract.Presenter {

    private final  CalenderContract.View mView;

    public CalenderPresenter(CalenderContract.View view) {
        this.mView = view;
    }


    @Override
    public void calculatTotalAmount(ArrayList<Charity> charities) {
        if(ValidationTools.isEmptyOrNull(charities)){
            return;
        }
        int totalAmount = 0 ;
        for(Charity charity : charities){
            totalAmount += charity.getPrice();
        }
        if(mView == null){
            return;
        }
        mView.setTotalAmount(totalAmount,UserTools.getInstance().getCurrencyType());
    }
}
