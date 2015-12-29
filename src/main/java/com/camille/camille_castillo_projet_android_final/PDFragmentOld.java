package com.camille.camille_castillo_projet_android_final;


import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PDFragmentOld extends Fragment {

    // private View btnPDF;
    private int currentPage = 0;
    private ImageView imageView;
    Button next;
    Button previous;

    View _vue;

    public PDFragmentOld() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _vue = inflater.inflate(R.layout.fragment_pdf, container, false);

        next = (Button) _vue.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
                render();
            }
        });

        previous = (Button) _vue.findViewById(R.id.previous);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage--;
                render();
            }
        });

        render();
        return _vue;
    }

    private void render() {
        try {
            imageView = (ImageView) _vue.findViewById(R.id.imageView);
            int REQ_WIDTH = 1;
            int REQ_HEIGHT = 1;
            REQ_WIDTH = imageView.getWidth();
            REQ_HEIGHT = imageView.getHeight();

            Bitmap bitmap = Bitmap.createBitmap(REQ_WIDTH, REQ_HEIGHT, Bitmap.Config.ARGB_4444);
            /*File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Front-LD.pdf");
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY));*/

            //InputStream is = getAssets().open("read_asset.txt");

            //ParcelFileDescriptor file = getActivity().getAssets().openFd("Front-LD.pdf").getParcelFileDescriptor();
            ParcelFileDescriptor file = this.getActivity().getAssets().openFd("Front-LD.pdf").getParcelFileDescriptor();
            // if(file.exists()) Log.w("Ex14:FRG1", "*** onClick **************************************************");

            // This is the PdfRenderer we use to render the PDF.
            PdfRenderer renderer = new PdfRenderer(file);

            if (currentPage < 0) {
                currentPage = 0;
            } else if (currentPage > renderer.getPageCount()) {
                currentPage = renderer.getPageCount() - 1;
            }

            Matrix m = imageView.getImageMatrix();
            Rect rect = new Rect(0, 0, REQ_WIDTH, REQ_HEIGHT);
            renderer.openPage(currentPage).render(bitmap, rect, m, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            imageView.setImageMatrix(m);
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
