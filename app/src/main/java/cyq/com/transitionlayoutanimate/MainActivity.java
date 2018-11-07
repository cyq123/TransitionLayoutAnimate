package cyq.com.transitionlayoutanimate;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private ViewGroup sceneRoot;
    private Scene aScene;
    private Scene anotherScene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);


        sceneRoot = findViewById(R.id.scene_root);
        //// Create the scenes
        aScene = Scene.getSceneForLayout(sceneRoot,R.layout.a_scene,this);
        anotherScene = Scene.getSceneForLayout(sceneRoot,R.layout.another_scene,this);

//        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fade_transition);

        radioGroup.check(R.id.check_scene1);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.check_scene1:
                TransitionManager.go(aScene);
                break;
            case R.id.check_scene2:
                TransitionManager.go(anotherScene);
                break;
            case R.id.check_scene3:
                // BEGIN_INCLUDE(transition_dynamic)
                //或者，可以在没有场景的情况下动态调用转换。
                //为此，我们首先调用TransitionManager.beginDelayedTransition()。
                TransitionManager.beginDelayedTransition(sceneRoot);
                //然后，我们可以像往常一样改变视图属性。
                View textview = sceneRoot.findViewById(R.id.text_view2);
                ViewGroup.LayoutParams layoutParams = textview.getLayoutParams();

                layoutParams.width = 300;
                layoutParams.height = 300;
                textview.setLayoutParams(layoutParams);
                // END_INCLUDE(transition_dynamic)
                break;
        }
    }
}
