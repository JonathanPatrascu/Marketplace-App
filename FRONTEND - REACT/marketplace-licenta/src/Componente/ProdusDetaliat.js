import React, { Component } from "react";
import "./ProdusDetaliat.css";
import Galerie from "./Galerie/Galerie";
import { Link } from "react-router-dom";

class ProdusDetaliat extends Component {
  state = { produs: {}, error: false, adaugatLaFavorite: false };

  getProduct = async () => {
    fetch(
      `http://localhost:8888/produs/findProdusById/${
        this.props.match.params.idProdus
      }`
    )
      .then(res => res.json())
      .then(res => this.setState({ produs: res }))
      .catch(e => this.setState({ error: true }));
  };

  componentDidMount() {
    this.getProduct();
  }

  adaugaLaFavorite = (props = this.props) => {
    const token = localStorage.getItem("token");

    fetch(
      `http://localhost:8888/listaDorinte/addProductToListaDorinte/${
        this.props.match.params.idProdus
      }`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        }
      }
    )
      .then(() => {
        this.setState({ adaugatLaFavorite: true });
        this.props.history.push("/listaDorinte");
      })
      .catch(e => console.log(e));
  };

  render() {
    if (!this.state.produs.idProdus) return null;

    return (
      <div className="pagina-produs">
        <div className="col-stanga">
          <div className="titlu-produs-detaliat">
            {this.state.produs.numeProdus}
          </div>

          <div className="sectiune-stanga">
            <div className="vanzator">
              Stare : {this.state.produs.stareProdus.descriereStareProdus}
              <br />
              Postat de : {this.state.produs.vanzator.numeUtilizator}
              <br />
              Data : {this.state.produs.dataPostare}
              <br />
              Locatie : {this.state.produs.vanzator.orasUtilizator}
              <br />
              <div className="transport-label"> Transport:</div>
              <div className="transport-value">
                {this.state.produs.transportGratuit ? "Inclus" : "Neinclus"}
              </div>
            </div>
          </div>
          <div className="sectiune-dreapta">
            <div className="pretProdus">{this.state.produs.pretProdus} Lei</div>
            <button
              type="submit"
              className="buton-lista-dorinte"
              onClick={this.adaugaLaFavorite}
            >
              {this.state.adaugatLaFavorite
                ? "Sterge de la favorite"
                : "Adauga La favorite"}
            </button>
          </div>
        </div>
        <div className="col-dreapta">
          <Galerie images={this.state.produs.imaginiProdus.split(",")} />
        </div>
        <div className="descriere-produs">
          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
          {this.state.produs.descriereProdus}
        </div>
      </div>
    );
  }
}

export default ProdusDetaliat;

{
  /* <div className="descriereProdusDetaliat">
{this.state.produs.descriereProdus}
</div> */
}

// <div className="col-dreapta-detali-produs">
//   <Galerie
//     images={["/img/brooklyn.jpg", "/img/falcon.jpg", "/img/penguin.jpg"]}
//   />
// </div>;
