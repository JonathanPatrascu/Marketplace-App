import React from "react";
import "./Produs.css";
import { Link } from "react-router-dom";
import image from "../defaultImage.jpg";

function Produs(props) {
  var id = props.idProdus;
  const tablouImagini = props.imaginiProdus;
  const imaginiInTablou = tablouImagini.split(",");
  console.log("imaginea:", imaginiInTablou[0]);

  return (
    <div className="produs-mic">
      <Link to={`/produsDetaliat/${id}`} className="link">
        <div className="div-imagine-produs-mic">
          <div
            class="imagine"
            style={{ backgroundImage: `url(${imaginiInTablou[0] || image})` }}
          />
        </div>
        <div className="nume-produs">{props.numeProdus}</div>
        <div className="pret">{props.pretProdus} Lei</div>
        <div className="conditie">{props.stareProdus.descriereStareProdus}</div>
        <div className="locatie">{props.vanzator.orasUtilizator}</div>
      </Link>
    </div>
  );
}
export default Produs;
