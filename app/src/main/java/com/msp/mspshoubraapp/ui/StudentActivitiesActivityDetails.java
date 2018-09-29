package com.msp.mspshoubraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.adapter.CommitteesExpandableRecyclerviewAdapter;
import com.msp.mspshoubraapp.data.ExpandableRecyclerViewItem;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.CommitteeEntity;
import com.msp.mspshoubraapp.db.StudentActivityEntity;
import com.squareup.picasso.Picasso;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StudentActivitiesActivityDetails extends AppCompatActivity {

    ImageView logoImageView;
    TextView titleTextView;
    TextView description;
    RecyclerView committees;
    ExpandableRecyclerViewAdapter adapter;
    List<ExpandableRecyclerViewItem> items;
    List<CommitteeEntity> committeesListItems;
    StudentActivityEntity studentActivityEntity;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentactivity_details);

        Intent intent = getIntent();
        getSupportActionBar().setTitle("Student Activities");
        studentActivityEntity = intent.getExtras().getParcelable("studentActivity");
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        logoImageView = findViewById(R.id.studentactivity_internal_logo);
        titleTextView = findViewById(R.id.studentactivity_internal_title);
        description = findViewById(R.id.studentactivity_description);

        titleTextView.setText(studentActivityEntity.getName());
        description.setText(studentActivityEntity.getDescription());
        Picasso.get().load(new File(studentActivityEntity.getImgLogo() + "/" + studentActivityEntity.getName())).into(logoImageView);

        committeesListItems = new ArrayList<>();
        committees = findViewById(R.id.committeesRV);
        committees.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        /*items.add(new ExpandableRecyclerViewItem("Committees", committeesListItems));
        adapter = new CommitteesExpandableRecyclerviewAdapter(items);
        committees.setAdapter(adapter);*/

        appDatabase = AppDatabase.getInstance(this);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                committeesListItems = appDatabase.committeesDao().loadAllCommittees(studentActivityEntity.getId());
                items.add(new ExpandableRecyclerViewItem("Committees", committeesListItems));
                adapter = new CommitteesExpandableRecyclerviewAdapter(items);
                committees.setAdapter(adapter);
            }
        });
        /*committees = findViewById(R.id.committeesRV);
        committees.setLayoutManager(new LinearLayoutManager(this));
        items = new ArrayList<>();
        items.add(new ExpandableRecyclerViewItem("Committees", committeesListItems));
        adapter = new CommitteesExpandableRecyclerviewAdapter(items);
        committees.setAdapter(adapter);*/
        //committeesListItems = new ArrayList<>();

        /*committeesListItems.add(new CommitteesListItem("Computer Committee", "Computer technology is so built into our lives that it's part of the surround of every artist\" -Steven Levy-\uD83D\uDCBB\n" +
                "\n" +
                "نركز شوية بقى في الأساس بتاعنا الجزء الtechnical اللي متأكدين إنه هيفيد كل طالب في حياته العملية مش بس الدراسية.\n" +
                "\n" +
                "ف صدقني مينفعش تبقى في تخصص computerولسة مقدمتش هنا\uD83E\uDD37\uD83C\uDFFB\u200D♀\n" +
                "\n" +
                "عشان كلنا بنكمل بعض\n" +
                "واكيد كل واحد في طريق دراسته محتاج حد يقف جنبه و يساعده\n" +
                "\n" +
                "دلوقتي جه معاد computer directorate،\n" +
                "لو انت عندك خبرة في\n" +
                "Machine Learning \n" +
                "Android Development\n" +
                "Web Development\n" +
                "او غيرهم\n" +
                "و كمان عايز إنك تقوي الskills اللي عندك إنك تتعلم إزاي تقدر تقف قدام ناس وتشرح أفكارك، قدم عشان هتلاقي committee متخصصة بس إنها تشتغل معاك على ده وتساعدك."));

        committeesListItems.add(new CommitteesListItem("POWER Committee", "Everything is just electricity and magnetism; everything is only an illusion. The reality is in the spiritual plane.\"\n" +
                "\n" +
                "*POWER DIRECTORATE*\uD83D\uDD0C\n" +
                "\n" +
                "1)- هتتعلم Basics of Electrical Engineering ، \n" +
                "لما تيجي تنزل summer training أو تشتغل عملي لازم يكون عندك minimum technical requirements عشان تكون aware بكل حاجة هتسمعها. \n" +
                "2)- هتعرف أية هيا مجالات Electric Power Engineering و أزاي تختار المجال الأنسب ليك و الخطوات اللي محتاج تعملها عشان تكون مميز فيه.\n" +
                "\n" +
                "المميز عندنا إننا هنحاول نقلل الفجوة اللي ما بين الشغل والدراسة، يعني مش بس أنت هتستفيد technically أنت هتشتغل عملي وهتكون متخرج عندك خبرة كبيرة بجانب الskills اللي هتنميها عندك وتميزك عن غيرك.✨"));

        committeesListItems.add(new CommitteesListItem("Marketing Committee", "Content is like fire, social media is the gasoline.\uD83D\uDD25\n" +
                "\n" +
                "لأي حد ميعرفش الموضوع ده بس الmarketing بقى دلوقتي من أكتر الحاجات اللي مطلوبة في كل المجالات ومطلوبة ليك حتى كشخص إنك تعرف تسوق نفسك صح علشان تبقى مميز عن غيرك، ف لازم يبقى عندك experienceكبيرة فيه.\uD83D\uDC4C\n" +
                "\n" +
                "حد يقولي : طيب أنا ال self experience دي هكتسبها إزاي و أنا لسه بدرس ف الكلية و كل الأماكن اللي أقدر أمارس فيها دا بيكونوا طالبين خريجين و معاهم ع الأقل خبرة سنة !!\n" +
                "طب بلاش دي ؛أنا أهو حد بيحب الماركتنج جدا و عندي استعداد إني أتعلم و اشتغل فيه بس مجال دراستي ف الكلية بعييد تماما عن الماركتنج أعمل إيه !؟؟\n" +
                "\n" +
                "قدم حتى لو مش عندك الخبرة اللي تخليكprofessionalمجرد حبك لتعلم و شغل الماركتنج كفاية إنه يخليك تكون member ف ال IEEE Marketing team...\uD83D\uDCAA\uD83D\uDE0D\uD83D\uDD25"));
        committeesListItems.add(new CommitteesListItem("HR Committee", "To win the marketplace you must first win the workplace\"\uD83D\uDE0C\n" +
                "\n" +
                "دايما كل لما تتقدر هتطلع أحسن حاجة عندك, وكل ما تتكدر هتبقى عارف إنه كله لمصلحتك عشان برضو تطلع أحسن حاجة عندك.\uD83D\uDE02\uD83E\uDD37\uD83C\uDFFB\u200D♀\n" +
                "\n" +
                "ف بدل ما تبقى خايف على طول من الinterviewوالشخص اللي بيعملهولك خليك مكانه وشوف قد إيه بياخد خبرة سواء في مجال العمل أو حتى خبرة شخصية في البني آدمين وإزاي بيشجعهم\uD83D\uDC96\n" +
                "\"People are the most difficult Mystery in this life \"-Albert Einstein\n" +
                "\n" +
                "جه الوقت إنك تنتقم وتقدم HRmember وتبقى أنت اللي بتخوف الناس\uD83D\uDE02\uD83D\uDE0E\n" +
                "\n"));
        committeesListItems.add(new CommitteesListItem("Coaching Committee", "\"Soft skills get little respect, but it’ll make or break your career\"\n" +
                "مين فينا مقابلش حد شاطر في الحاجة اللي هوبيعملها بس مبيعرفش يوصلها للي قدامه بسبب إنه معندوش الskills دي\n" +
                "\n" +
                "كلنا عارفين إن الخبرة اللي أنت بتاخدها من الstudent activities بتبقى مطلوبة جدا في سوق العمل والsoft skillsاللي انت بتكتسبها بتكون أهم من الجزء الtechnicalكمان\n" +
                "\n" +
                "إيه رأيك لو بقيت أنت أصلا trainer لل skills دي وانت الي بتعمل ال content و بتدرب باقي ال teams عليها؟ ساعتها أنت الخبرة بتاعتك هتبقى أضعاف\uD83D\uDC4C\n" +
                "Coaching is the universal language of change and learning"));*/



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //NavUtils.navigateUpFromSameTask(this);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
