package uzap.com.ua.twohourdelivery.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class TestTask extends AsyncTask<Void, Void, Void> {

    private ProgressDialog pDialog;
    private Context context;

    public TestTask(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog.setCancelable(false);
        pDialog.setMessage("Обновление заявок");
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        pDialog.dismiss();
    }
}
