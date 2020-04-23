import React, { Component } from "react";
import "./PaginaStart.css";
import startpageImage from "../startpageimage.jpg";
class PaginaStart extends Component {
  render() {
    return (
      <div className="pagina-start">
        <table className="tabel" cellPadding="0" cellSpacing="0">
          <tr className="spatiu-tabel">
            <td>
              <img
                className="startpage-image"
                src={startpageImage}
                alt="startpage-img"
              />
            </td>
            <td className="sectiune-text">
              <h1 className="header-text">Bine ai venit!</h1>
              <div className="text-paragraf">
                Cand e timpul sa iti schimbi instrumentul muzical, vinde-l pe
                cel vechi aici! Acest Marketplace este locul perfect unde poti
                vinde, cumpara, sau inchiria orice fel de echipament muzical.
                Serviciile oferite de aceasta platforma online sunt in totaitate
                gratuite, nu se percepe niciun comision de tranzactie. Scopul
                nostru principal este de a ajuta pe cei pasionati de muzica sa
                se conecteze si sa dispuna de un mediu in care pot vinde,
                cumpara sau schimba echipamente muzicale. Iti dorim o experienta
                minunata in acest Marketplace!
              </div>
            </td>
          </tr>
        </table>
      </div>
    );
  }
}

export default PaginaStart;
