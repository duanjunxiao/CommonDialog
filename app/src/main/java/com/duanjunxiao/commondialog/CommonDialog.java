package com.duanjunxiao.commondialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by duanjunxiao on 16/9/10.
 * extends Dialog
 */
public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private boolean isShortDialog = true;
        private Context context;
        private CharSequence title;
        private CharSequence message;
        private CharSequence positiveButtonText;
        private CharSequence negativeButtonText;
        private int positiveButtonBgId;
        private int negativeButtonBgId;

        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setIsShortDialog(boolean isShortDialog) {
            this.isShortDialog = isShortDialog;
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = context.getText(message);
            return this;
        }

        public Builder setTitle(int title) {
            this.title = context.getText(title);
            return this;
        }

        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setPositiveButton(CharSequence positiveButtonText, DialogInterface.OnClickListener listener, int bgId) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            this.positiveButtonBgId = bgId;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener, int bgId) {
            this.positiveButtonText = context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            this.positiveButtonBgId = bgId;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
            this.setPositiveButton(context.getText(positiveButtonText), listener, 0);
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.setPositiveButton(positiveButtonText, listener, 0);
            return this;
        }

        public Builder setNegativeButton(CharSequence negativeButtonText, DialogInterface.OnClickListener listener, int bgId) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            this.negativeButtonBgId = bgId;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener, int bgId) {
            this.negativeButtonText = context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            this.negativeButtonBgId = bgId;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
            this.setNegativeButton(context.getText(negativeButtonText), listener, 0);
            return this;
        }

        public Builder setNegativeButton(CharSequence negativeButtonText, DialogInterface.OnClickListener listener) {
            this.setNegativeButton(negativeButtonText, listener, 0);
            return this;
        }

        public CommonDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // set the dialog custom Theme
            final CommonDialog dialog = new CommonDialog(context, R.style.TransparentBackgroundDialog);
            View layout = null;
            if (isShortDialog) {
                layout = inflater.inflate(R.layout.layout_builder_common_dialog_short, null);
                dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                // find the dialog view
                TextView messageView = (TextView) layout.findViewById(R.id.message);
                TextView titleView = (TextView) layout.findViewById(R.id.title);
                Button positiveButton = (Button) layout.findViewById(R.id.positiveButton);
                Button negativeButton = (Button) layout.findViewById(R.id.negativeButton);
                // set the dialog title
                if (title != null) {
                    titleView.setText(title);
                } else {
                    titleView.setVisibility(View.GONE);
                }
                // set the dialog content
                if (message != null) {
                    messageView.setText(message);
                } else {
                    messageView.setVisibility(View.GONE);
                }
                // set the confirm button
                if (positiveButtonText != null) {
                    positiveButton.setText(positiveButtonText);
                    if (positiveButtonClickListener != null) {
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            }
                        });
                    } else {
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                    if (positiveButtonBgId != 0) {
                        positiveButton.setBackgroundDrawable(context.getResources().getDrawable(positiveButtonBgId));
                    }
                } else {
                    positiveButton.setVisibility(View.GONE);
                }
                // set the cancel button
                if (negativeButtonText != null) {
                    negativeButton.setText(negativeButtonText);
                    if (negativeButtonClickListener != null) {
                        negativeButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                            }
                        });
                    } else {
                        negativeButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                    if (negativeButtonBgId != 0) {
                        negativeButton.setBackgroundDrawable(context.getResources().getDrawable(negativeButtonBgId));
                    }
                } else {
                    negativeButton.setVisibility(View.GONE);
                }
            } else {
                layout = inflater.inflate(R.layout.layout_builder_common_dialog, null);
                dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                // find the dialog view
                TextView messageView = (TextView) layout.findViewById(R.id.message);
                TextView titleView = (TextView) layout.findViewById(R.id.title);
                ScrollView contentView = (ScrollView) layout.findViewById(R.id.content);
                Button positiveButton = (Button) layout.findViewById(R.id.positiveButton);
                // set the dialog title
                if (title != null) {
                    titleView.setText(title);
                } else {
                    titleView.setVisibility(View.GONE);
                }
                // set the dialog content
                if (message != null) {
                    messageView.setText(message);
                } else {
                    contentView.setVisibility(View.GONE);
                }
                // set the confirm button
                if (positiveButtonText != null) {
                    positiveButton.setText(positiveButtonText);
                    if (positiveButtonClickListener != null) {
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                            }
                        });
                    } else {
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                if (dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        });
                    }
                    if (positiveButtonBgId != 0) {
                        positiveButton.setBackgroundDrawable(context.getResources().getDrawable(positiveButtonBgId));
                    }
                } else {
                    positiveButton.setVisibility(View.GONE);
                }
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }

    public static Builder createBuilder(Context context){
        return new Builder(context);
    }

}
