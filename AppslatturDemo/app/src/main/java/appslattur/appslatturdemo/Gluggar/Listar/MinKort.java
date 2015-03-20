package appslattur.appslatturdemo.Gluggar.Listar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import appslattur.appslatturdemo.R;

public class MinKort extends Activity {
    static PutExtrasHere extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_min_kort);
    }

    private class PutExtrasHere{
        private ImageView imgview;
        private TextView textview;
        private PutExtrasHere(){

        }
        public void setImgview(ImageView i){
            this.imgview = i;
        }
        public void setTextview(TextView t){
            this.textview = t;
        }

    }

    public static class CardFragment extends Fragment {
        public CardFragment(){}
        public CardFragment newInstance(Bundle bundle){
            return new CardFragment();
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.cardfragment,container,false);

            extras.setImgview((ImageView)rootView.findViewById(R.id.cardplace));
            extras.setTextview((TextView)rootView.findViewById(R.id.detailsplace));

            return rootView;
        }
    }

}
