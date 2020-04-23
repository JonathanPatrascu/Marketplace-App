import React from "react";
import "./ProdusUtilizator.css";
import { Link } from "react-router-dom";
import defaultImage from "../defaultImage.jpg";

function ProdusUtilizator(props) {
  const idProdus = props.idProdus;
  const tablouImagini = props.imaginiProdus;
  const imagini = tablouImagini.split(",");
  console.log("id produs utilizator:", idProdus);

  const stergeProdusUtilizator = () => {
    const token = localStorage.getItem("token");
    fetch(`http://localhost:8888/produs/deleteProdus/${idProdus}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    })
      .then(() => props.stergeProdus(idProdus))
      .catch(e => console.log(e));
  };

  return (
    <div className="produs-utilizator-mic">
      <Link
        to={`/produsDetaliat/${idProdus}`}
        className="link-produs-utilizator"
      >
        <div
          className="imagine-produs-utilizator"
          style={{ backgroundImage: `url(${imagini[0] || defaultImage})` }}
        />
      </Link>
      <Link
        to={`/produsDetaliat/${idProdus}`}
        className="link-produs-utilizator"
      >
        <div className="div-nume-produs-utilizator">{props.numeProdus}</div>
      </Link>

      <div className="div-sterge-produs-utilizator">
        <button
          onClick={stergeProdusUtilizator}
          type="submit"
          className="buton-sterge-produs-utilizator"
        >
          Dezactiveaza
        </button>
      </div>

      <div className="div-modifica-produs-utilizator">
        <Link to={`/editareProdus/${idProdus}`}>
          <button type="submit" className="buton-modifica-produs-utilizator">
            Editeaza articol
          </button>
        </Link>
      </div>

      <hr className="linie-orizontalprodus-utilizator" />
    </div>
  );
}

export default ProdusUtilizator;
