
package com.example.marui.a571hw9_android;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
/**
 * Created by marui on 11/26/16.
 */

public class billFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.bill_main, container, false);
    }
}
