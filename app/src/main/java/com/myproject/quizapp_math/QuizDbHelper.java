package com.myproject.quizapp_math;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.myproject.quizapp_math.Questions;
import com.myproject.quizapp_math.QuizContract.*;



import java.util.ArrayList;



public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MathQuiz.db";
    private static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

     public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_lEVELS_ID + " INTEGER " +
                //
                " ) ";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void addQuestions(Questions question){

        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_lEVELS_ID,question.getLevels());

        db.insert(QuestionTable.TABLE_NAME,null,cv);

    }

    private void fillQuestionsTable() {


        Questions easy1 = new Questions("Find the sum.\n10 + 5 = ?","10","9","11", "15", 4,
                Questions.EASYLEVEL);
        addQuestions(easy1);
        Questions easy2 = new Questions("Find the sum.\n9 + 4 = ?","12","14","13", "15", 3,
                Questions.EASYLEVEL);
        addQuestions(easy2);

        Questions easy3 = new Questions("Find the sum.\n8 + 3 = ?","12","14","16", "11", 4,
                Questions.EASYLEVEL);
        addQuestions(easy3);

        Questions easy4 = new Questions("Find the sum.\n7 + 2 = ?","9","10","8", "11", 1,
                Questions.EASYLEVEL);
        addQuestions(easy4);

        Questions easy5 = new Questions("Find the sum.\n6 + 1 = ?","8","7","6", "5", 2,
                Questions.EASYLEVEL);
        addQuestions(easy5);

        Questions easy6 = new Questions("Find the sum.\n5 + 5 = ?","10","9","11", "8", 1,
                Questions.EASYLEVEL);
        addQuestions(easy6);

        Questions easy7 = new Questions("Find the sum.\n4 + 4 = ?","9","7","6", "8", 4,
                Questions.EASYLEVEL);
        addQuestions(easy7);

        Questions easy8 = new Questions("Find the sum.\n3 + 3 = ?","2","4","6", "8", 3,
                Questions.EASYLEVEL);
        addQuestions(easy8);

        Questions easy9 = new Questions("Find the sum.\n2 + 2 = ?","2","4","6", "8", 2,
                Questions.EASYLEVEL);
        addQuestions(easy9);

        Questions easy10 = new Questions("Find the sum.\n1 + 1 = ?","2","4","6", "8", 1,
                Questions.EASYLEVEL);
        addQuestions(easy10);

        Questions easy11 = new Questions("Find the difference.\n10 - 1 = ?","9","8","7", "6", 1,
                Questions.EASYLEVEL);
        addQuestions(easy11);

        Questions easy12 = new Questions("Find the difference.\n9 - 2 = ?","2","5","6", "7", 4,
                Questions.EASYLEVEL);
        addQuestions(easy12);

        Questions easy13 = new Questions("Find the difference.\n8 - 3 = ?","2","4","5", "8", 3,
                Questions.EASYLEVEL);
        addQuestions(easy13);

        Questions easy14 = new Questions("Find the difference.\n7 - 4 = ?","3","4","5", "2", 1,
                Questions.EASYLEVEL);
        addQuestions(easy14);

        Questions easy15 = new Questions("Find the difference.\n5 - 6 = ?","0","1","2", "-1", 4,
                Questions.EASYLEVEL);
        addQuestions(easy15);

        Questions easy16 = new Questions("Find the difference.\n5 - 1 = ?","2","3","4", "6", 3,
                Questions.EASYLEVEL);
        addQuestions(easy16);

        Questions easy17 = new Questions("Find the difference.\n4 - 2 = ?","2","4","6", "8", 1,
                Questions.EASYLEVEL);
        addQuestions(easy17);

        Questions easy18 = new Questions("Find the difference.\n3 - 1 = ?","2","3","1", "0", 1,
                Questions.EASYLEVEL);
        addQuestions(easy18 );

        Questions easy19 = new Questions("Find the difference.\n2 - 1 = ?","2","1","0", "-1", 2,
                Questions.EASYLEVEL);
        addQuestions(easy19);

        Questions easy20 = new Questions("Find the difference.\n1 - 1 = ?","2","0","1", "-1", 2,
                Questions.EASYLEVEL);
        addQuestions(easy20);








        Questions normal1 = new Questions("Find the sum.\n22 + 54 =","76","86","66", "96", 1,
                Questions.NORMALLEVEL);
        addQuestions(normal1);
        Questions normal2 = new Questions("Find the sum.\n44 + 38 =","92","82","72", "62", 2,
                Questions.NORMALLEVEL);
        addQuestions(normal2);
        Questions normal7 = new Questions("Find the difference.\n100 - 50 =","20","40","50", "80", 3,
                Questions.NORMALLEVEL);
        addQuestions(normal7);
        Questions normal12 = new Questions("Find the difference.\n21- 11 =","7","8","9", "10", 4,
                Questions.NORMALLEVEL);
        addQuestions(normal12);
        Questions normal13 = new Questions("Find the product.\n6 x 6 =","36","46","26", "40", 1,
                Questions.NORMALLEVEL);
        addQuestions(normal13);
        Questions normal14 = new Questions("Find the product.\n7 x 9 =","73","63","53", "83", 2,
                Questions.NORMALLEVEL);
        addQuestions(normal14);
        Questions normal15 = new Questions("Find the product.\n8 x 7 =","36","46","56", "66", 3,
                Questions.NORMALLEVEL);
        addQuestions(normal15);
        Questions normal16 = new Questions("Find the product.\n9 x 8 =","52","62","82", "72", 4,
                Questions.NORMALLEVEL);
        addQuestions(normal16);
        Questions normal17 = new Questions("Find the dividend.\n8 ÷ 2 =","3","2","4", "1", 1,
                Questions.NORMALLEVEL);
        addQuestions(normal17);
        Questions normal20 = new Questions("Find the dividend.\n6 ÷ 1 =","2","4","6", "8", 3,
                Questions.NORMALLEVEL);
        addQuestions(normal20);





        Questions hard1 = new Questions("Find the product.\n22 x 22 =","484","494","584", "594", 1,
                Questions.HARDLEVEL);
        addQuestions(hard1);
        Questions hard2 = new Questions("Find the product.\n53 x 10 =","630","530","520", "620", 2,
                Questions.HARDLEVEL);
        addQuestions(hard2);
        Questions hard3 = new Questions("Find the product.\n14 x 20=","270","380","280", "290", 3,
                Questions.HARDLEVEL);
        addQuestions(hard3);
        Questions hard4 = new Questions("Find the product.\n21 x 10 =","220","120","110", "210", 4,
                Questions.HARDLEVEL);
        addQuestions(hard4);
        Questions hard5 = new Questions("Find the product.\n28 x 12 =","336","326","236", "326", 1,
                Questions.HARDLEVEL);
        addQuestions(hard5);
        Questions hard6 = new Questions("Find the dividend.\n105 ÷ 5 =","22","21","23", "24", 2,
                Questions.HARDLEVEL);
        addQuestions(hard6);
        Questions hard7 = new Questions("Find the dividend.\n80 ÷ 4 =","50","40","20", "30", 3,
                Questions.HARDLEVEL);
        addQuestions(hard7);
        Questions hard8 = new Questions("Find the dividend.\n200 ÷ 5 =","30","50","60", "40", 4,
                Questions.HARDLEVEL);
        addQuestions(hard8);
        Questions hard10 = new Questions("Find the dividend.\n85 ÷ 5 =","18","17","16", "15", 2,
                Questions.HARDLEVEL);
        addQuestions(hard10);
        Questions hard11 = new Questions("Problem Solving:\nIf it takes 5/6 yards of fabric to make a dress, then how many yards will it take to make 8 dresses?","2 2/4","4 1/2","6 2/3", "7 1-3", 3,
                Questions.HARDLEVEL);
        addQuestions(hard11);





    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);

    }


    public ArrayList<Questions> getAllQuestionsWithCategoryAndLevels(int levelsID) {

        ArrayList<Questions> questionList = new ArrayList<>();
        db = getReadableDatabase();



        String Projection[] = {

                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,
                QuestionTable.COLUMN_lEVELS_ID
        };

        String selection = QuestionTable.COLUMN_lEVELS_ID + " = ? " ;
        String selectionArgs[] = {String.valueOf(levelsID)};

        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                selection,
                selectionArgs,
                null,
                null,
                null);


        if (c.moveToFirst()) {
            do {

                Questions question = new Questions();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

                question.setLevels(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_lEVELS_ID)));

                questionList.add(question);

            } while (c.moveToNext());

        }
        c.close();
        return questionList;


    }


}
