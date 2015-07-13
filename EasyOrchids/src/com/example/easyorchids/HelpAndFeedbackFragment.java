package com.example.easyorchids;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * 
 * Collect user feedback and start an email client intent to send the feedback.
 *
 */
public class HelpAndFeedbackFragment extends Fragment {

	private Button feedbackBtn;
	private EditText textTo;
	private EditText textMessage;
	private EditText textSubject;

	public HelpAndFeedbackFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.help_feedback_view,
				container, false);

		textTo = (EditText) rootView.findViewById(R.id.textTo);
		textMessage = (EditText) rootView.findViewById(R.id.message_text);
		textSubject = (EditText) rootView.findViewById(R.id.subject_text);

		feedbackBtn = (Button) rootView.findViewById(R.id.feedback_btn);
		feedbackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String to = textTo.getText().toString();
				String message = textMessage.getText().toString();
				String subject = textSubject.getText().toString();

				Intent mEmail = new Intent(Intent.ACTION_SEND);
				mEmail.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
				mEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
				mEmail.putExtra(Intent.EXTRA_TEXT, message);

				// prompts to choose email client
				mEmail.setType("message/rfc822");

				startActivity(Intent.createChooser(mEmail,
						"Choose an email client to send your feedback!"));

			}
		});
		return rootView;
	}

}