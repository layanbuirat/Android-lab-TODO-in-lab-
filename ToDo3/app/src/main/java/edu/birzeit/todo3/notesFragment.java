package edu.birzeit.todo3;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.AnimationUtils;
import android.database.Cursor;

public class notesFragment extends Fragment {
    private EditText editTextTitle, editTextContent;
    private Button buttonSaveNote, buttonClearAll;
    private TextView textViewNoteCount, textViewLatestNote;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        initViews(view);
        databaseHelper = new DatabaseHelper(getActivity());

        TextView titleText = view.findViewById(R.id.textViewNotesTitle);
        titleText.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));

        updateNoteCount();
        displayLatestNote();

        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllNotes();
            }
        });

        return view;
    }

    private void initViews(View view) {
        editTextTitle = view.findViewById(R.id.editTextNoteTitle);
        editTextContent = view.findViewById(R.id.editTextNoteContent);
        buttonSaveNote = view.findViewById(R.id.buttonSaveNote);
        buttonClearAll = view.findViewById(R.id.buttonClearAllNotes);
        textViewNoteCount = view.findViewById(R.id.textViewNoteCount);
        textViewLatestNote = view.findViewById(R.id.textViewLatestNote);
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(getActivity(), "Title and content are required", Toast.LENGTH_SHORT).show();
            return;
        }

        buttonSaveNote.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_button));

        long result = databaseHelper.addNote(title, content);

        if (result != -1) {
            Toast.makeText(getActivity(), "Note saved success", Toast.LENGTH_SHORT).show();
            editTextTitle.setText("");
            editTextContent.setText("");
            updateNoteCount();
            displayLatestNote();
        } else {
            Toast.makeText(getActivity(), "Failed to save", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateNoteCount() {
        int count = databaseHelper.getNoteCount();
        textViewNoteCount.setText("Total Note: " + count);
    }

    private void displayLatestNote() {
        Cursor cursor = databaseHelper.getLatestNote();
        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            String date = cursor.getString(cursor.getColumnIndexOrThrow("created_date"));

            String noteText = "Title: " + title + "\n" +
                    "Content: " + content + "\n" +
                    "Time: " + date;
            textViewLatestNote.setText(noteText);
            cursor.close();
        } else {
            textViewLatestNote.setText("No notes");
        }
    }

    private void clearAllNotes() {
        int deletedRows = databaseHelper.deleteAllNotes();
        if (deletedRows > 0) {
            Toast.makeText(getActivity(), "All  deleted", Toast.LENGTH_SHORT).show();
            updateNoteCount();
            textViewLatestNote.setText("No notes");
        }
    }
}
