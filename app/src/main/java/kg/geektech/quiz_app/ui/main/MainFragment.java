package kg.geektech.quiz_app.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.quiz_app.R;
import kg.geektech.quiz_app.databinding.FragmentMainBinding;
import kg.geektech.quiz_app.models.Categories;
import kg.geektech.quiz_app.models.TriviaCategories;
import kg.geektech.quiz_app.ui.quiz.QuizQuestionFragment;
import kg.geektech.quiz_app.utils.Seekbar;
import kg.geektech.quiz_app.utils.SpinnerItemClick;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    Integer pos;
    Integer seekBarPos = 1, category;
    String valueOfDiffSpin, categoryName;
    TextView amount;

    public static final String EXTRA_AMOUNT = "slider";
    public static final String EXTRA_CATEGORY = "categoryName";
    public static final String EXTRA_CATEGORY_POS = "categoryPos";
    public static final String EXTRA_DIFFICULTY = "difficulty";
    FragmentMainBinding fragmentMainBinding;
    MainViewModel mMainViewModel;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return fragmentMainBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        fragmentMainBinding.setViewModel(mViewModel);
        mViewModel.getCategories();
        amount = fragmentMainBinding.amountSlider;
        mViewModel.trivia.observe(getActivity(), new Observer<Categories>() {
            @Override
            public void onChanged(Categories categories) {
                List<TriviaCategories> mod;
                mod = categories.getTriviaCategories();
                List<String> name = new ArrayList<>();
                for (TriviaCategories bravo : mod) {
                    name.add(bravo.getName());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), R.layout.support_simple_spinner_dropdown_item, name);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    fragmentMainBinding.spinner1.setAdapter(adapter);
                    fragmentMainBinding.spinner1.setOnItemSelectedListener(new SpinnerItemClick() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            super.onItemSelected(parent, view, position, id);
                            category = categories.getTriviaCategories().get(0).getId();
                            categoryName = (categories.getTriviaCategories().get(position).getName());
                            pos = position;
                            Log.e("ololo", "onItemSelected: " + categories.getTriviaCategories().get(position).getName());


                        }


//                            for (int i = 0; i < mod.size(); i++) {
//                                category=categories.getTriviaCategories().get(i).getId();}


                    });

                }
            }
        });
        onStartBtn();
        setSpinnerDifficulty();
        onSeekBar();


    }

    public void setSpinnerDifficulty() {
        if (getActivity() != null) {
            ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.difficulty, android.R.layout.simple_spinner_item);
            fragmentMainBinding.spinner2.setAdapter(categoryAdapter);
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fragmentMainBinding.spinner2.setOnItemSelectedListener(new SpinnerItemClick() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    super.onItemSelected(parent, view, position, id);
                    valueOfDiffSpin = fragmentMainBinding.spinner2.getSelectedItem().toString();
                }
            });
        }
    }

    public void onSeekBar() {
        fragmentMainBinding.seekBar.setOnSeekBarChangeListener(new Seekbar() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("ololo", "onProgressChanged: " + progress);
                super.onProgressChanged(seekBar, progress, fromUser);
                if (progress > 1)
                    amount.setText(": " + progress);
                seekBar.setMin(1);
                seekBarPos = seekBar.getProgress();
                Log.d("ololo", seekBarPos + "");
            }


        });
    }

    public void onStartBtn() {
        fragmentMainBinding.buttonStart.setOnClickListener(v -> {
            QuizQuestionFragment fragment = new QuizQuestionFragment();
            Bundle result = new Bundle();
            result.putInt(EXTRA_AMOUNT, seekBarPos);
            result.putInt(EXTRA_CATEGORY_POS, category);
            result.putString(EXTRA_CATEGORY, categoryName);
            Log.e("ololo", "result. putString: " + categoryName);
            result.putString(EXTRA_DIFFICULTY, valueOfDiffSpin);
            //   result.putString("resultCategory", categoryName);
            fragment.setArguments(result);
            Log.e("yoyo", result.toString());
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).hide(MainFragment.this).addToBackStack(null).commit();
            }

        });
    }
}
