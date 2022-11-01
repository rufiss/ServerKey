package com.example.demo;

import java.io.IOException;
import com.lacunasoftware.pkiexpress.PadesSize;
import com.lacunasoftware.pkiexpress.PadesVisualAutoPositioning;
import com.lacunasoftware.pkiexpress.PadesVisualImage;
import com.lacunasoftware.pkiexpress.PadesVisualRectangle;
import com.lacunasoftware.pkiexpress.PadesVisualRepresentation;
import com.lacunasoftware.pkiexpress.PadesVisualText;

public class PadesVisualElements {
    	/**
	 * This method is called by the get() method. It contains examples of signature visual
	 * representation positionings.
	 */
	public static PadesVisualRepresentation getVisualRepresentation() throws IOException {

		// Create a visual representation for the signature.
		PadesVisualRepresentation vr = new PadesVisualRepresentation();

		// The tags {{name}} and {{national_id}} will be substituted according to the
		// user's certificate:
		//
		//      name        : full name of the signer;
		//      national_id : if the certificate is ICP-Brasil, contains the signer's
		//                    CPF.
		//
		// For a full list of the supported tags, see:
		// https://docs.lacunasoftware.com/articles/pki-express/pades-tags.html
		//
		PadesVisualText text = new PadesVisualText("Signed by {{name}} ({{national_id}})", true);
		// Set font size.
		text.setFontSize(13.0);
		// Create text container and add to PadesVisualText object.
		PadesVisualRectangle container = new PadesVisualRectangle();
		container.setVerticalStretch(0.2, 0.2);
		container.setHorizontalStretch(0.2, 0.2);
		text.setContainer(container);
		// Add text to visual representation.
		vr.setText(text);

		// Create an instance of PadesVisualImage class.
		PadesVisualImage image = new PadesVisualImage();
		// Set the path of the image
		image.setContent("src//main//java//com//example//resources//stamp.png");
		// Add image to visual representation.
		vr.setImage(image);

		// Create an instance of PadesVisualAutoPositioning class. This class will create a
		// custom auto positioning for the visual representation.
		PadesVisualAutoPositioning position = new PadesVisualAutoPositioning();
		// Set the page number. Negative values represent pages counted form the end of the
		// document (-1 is the last page)
		position.setPageNumber(-1);
		// Set the row spacing between signatures. The signatures will be placed in the
		// container side by side. If there's no room left, the signatures will "wrap" to
		// the next row. This value specifies the vertical distance between rows.
		position.setRowSpacing(0.0);
		// Specify the size of each signature rectangle.
		PadesSize size = new PadesSize(8.0, 4.94);
		position.setSignatureRectangleSize(size);
		// Create the position container and add to PadesVisualPosition object.
		PadesVisualRectangle positionContainer = new PadesVisualRectangle();
		positionContainer.setHeightBottomAnchored(4.94, 1.5);
		positionContainer.setHorizontalStretch(1.5, 1.5);
		position.setContainer(positionContainer);
		// Add position to visual representation.
		vr.setPosition(position);

		return vr;
	}

}
