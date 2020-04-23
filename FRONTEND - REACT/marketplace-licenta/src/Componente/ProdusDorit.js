import React from "react";
import "./ProdusDorit.css";
import { Link } from "react-router-dom";
import defaultImage from "../defaultImage.jpg";

function ProdusDorit(props) {
  const idProdus = props.idProdus;

  const stergeProdusDorit = () => {
    const token = localStorage.getItem("token");
    fetch(
      `http://localhost:8888/listaDorinte/deleteProductFromListaDorinte/${idProdus}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      }
    )
      .then(() => props.stergeProdus(idProdus))
      .catch(e => console.log(e));
  };

  const tablouImagini = props.imaginiProdus;
  const imaginiInTablou = tablouImagini.split(",");

  return (
    <div className="produs-dorit-mic">
      <Link to={`/produsDetaliat/${idProdus}`} className="link">
        <div
          className="imagine-produs-dorit"
          style={{
            backgroundImage: `url(${imaginiInTablou[0] || defaultImage})`
          }}
        />
      </Link>
      <Link to={`/produsDetaliat/${idProdus}`} className="link">
        <div className="nume-produs-dorit">Nume produs: {props.numeProdus}</div>
        <div className="pret-produs-dorit">{props.pretProdus} Lei</div>
        <div className="locatie-produs-dorit">
          {props.vanzator.orasUtilizator}
        </div>
      </Link>

      <Link to={`/listaDorinte`} className="link">
        <div className="div-sterge-produs-dorit">
          <button
            onClick={stergeProdusDorit}
            type="submit"
            className="buton-sterge-produs-dorit"
          >
            Elimina din lista
          </button>
        </div>
      </Link>

      <div className="linie-orizontala">
        <hr />
      </div>
    </div>
  );
}

export default ProdusDorit;
