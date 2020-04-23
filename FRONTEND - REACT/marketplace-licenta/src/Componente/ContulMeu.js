import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import { Link } from "react-router-dom";
import ProdusUtilizator from "./ProdusUtilizator";

import "./ContulMeu.css";

class ContulMeu extends Component {
  state = { utilizator: {}, produseUtilizator: [] };

  getUtilizator = (props = this.props) => {
    const { token } = this.props;

    fetch(`http://localhost:8888/utilizator/findUtilizatorById`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    })
      .then(res => res.json())
      .then(res => this.setState({ utilizator: res }))
      .catch(e => console.log(e));
  };

  getProduseUtilizator = (props = this.props) => {
    const { token } = this.props;

    fetch(`http://localhost:8888/produs/findProductsForUser`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    })
      .then(res => res.json())
      .then(res => this.setState({ produseUtilizator: res }))
      .catch(e => console.log(e));
  };

  stergeProdus = idProdus => {
    const { produseUtilizator } = this.state;
    this.setState({
      produseUtilizator: produseUtilizator.filter(
        produseUtilizator => produseUtilizator.idProdus !== idProdus
      )
    });
  };

  componentDidMount() {
    this.getUtilizator();
    this.getProduseUtilizator();
  }

  render() {
    console.log("user products:", this.state.produseUtilizator);

    const produsUser = this.state.produseUtilizator.map(produs => (
      <div key={produs.idProdus}>
        <ProdusUtilizator {...produs} stergeProdus={this.stergeProdus} />
      </div>
    ));
    return (
      <div className="contul-meu-container-mare">
        <h1 className="heading-contul-meu">Informatii profil</h1>

        <div className="cont-sectiune-stanga">
          <div className="container-imagine">
            <img
              src={this.state.utilizator.imagineUtilizator}
              alt="ImagineUtilizator"
              className="imagineProfilUtilizator"
            />
          </div>
        </div>
        <div className="cont-sectiune-dreapta">
          <table className="informatii-utilizator">
            <tr>
              <td className="label">Nume:</td>
              <td className="value">{this.state.utilizator.numeUtilizator}</td>
            </tr>
            <tr>
              <td className="label">Username:</td>
              <td className="value">
                {this.state.utilizator.usernameUtilizator}
              </td>
            </tr>
            <tr>
              <td className="label">Email:</td>
              <td className="value">{this.state.utilizator.emailUtilizator}</td>
            </tr>
            <tr>
              <td className="label">Adresa :</td>
              <td className="value">
                {this.state.utilizator.adresaUtilizator}, &nbsp;
                {this.state.utilizator.orasUtilizator}
              </td>
            </tr>
            <tr>
              <td className="label">Telefon:</td>
              <td className="value">
                {this.state.utilizator.telefonUtilizator}
              </td>
            </tr>
          </table>

          <div />
        </div>
        <div className="diviziune-buton-editare-date">
          <Link to={`/editareInformatiiProfil`}>
            <button className="buton-editare-date">Editeaza profil</button>
          </Link>
        </div>
        <div className="div-produsele-utilizatorului">
          <h1 className="heading-produsele-mele">Produsele mele</h1>
          <div className="container-produsele-utilizatorului">{produsUser}</div>
        </div>
      </div>
    );
  }
}

export default ContulMeu;
