import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import base64 from "image-to-base64";
import "./EditareProdus.css";
import ImageUploader from "react-images-upload";
import { withRouter } from "react-router-dom";

class EditareProdus extends Component {
  state = { produs: {}, imaginiProdus: [], salvareSuccess: false };

  constructor(props) {
    super(props);

    this.onDrop = this.onDrop.bind(this);
  }

  changeTitlu = event => {
    this.setState({
      produs: {
        ...this.state.produs,
        numeProdus: event.target.value
      }
    });
  };

  changePret = event => {
    this.setState({
      produs: { ...this.state.produs, pretProdus: event.target.value }
    });
  };

  changeBrand = event => {
    this.setState({
      produs: { ...this.state.produs, brandProdus: event.target.value }
    });
  };

  changeTransport = event => {
    this.setState({
      produs: { ...this.state.produs, transportGratuit: event.target.value }
    });
  };

  changeStare = event => {
    this.setState({
      produs: {
        ...this.state.produs,
        stareProdus: { idStareProdus: event.target.value }
      }
    });
  };

  changeDescriere = event => {
    this.setState({
      produs: { ...this.state.produs, descriereProdus: event.target.value }
    });
  };

  getProdus = async () => {
    fetch(
      `http://localhost:8888/produs/findProdusById/${
        this.props.match.params.idProdus
      }`
    )
      .then(res => res.json())
      .then(res => this.setState({ produs: res }))
      .catch(e => this.setState({ error: true }));
  };

  onDrop = imaginiProdus => {
    this.setState({
      imaginiProdus: imaginiProdus.map(imagine => URL.createObjectURL(imagine))
    });
  };

  componentDidMount() {
    this.getProdus();
  }

  uploadImages = async imagesArray =>
    Promise.all(
      imagesArray.map(image => {
        return fetch("https://api.imgur.com/3/image", {
          method: "POST",
          body: JSON.stringify({
            image
          }),
          headers: {
            "Content-Type": "application/json",
            Authorization: `Client-ID f95514a5d23f091`
          }
        })
          .then(res => res.json())
          .then(res => res.data.link);
      })
    );

  editeazaProdus = async event => {
    event.preventDefault();
    const { produs } = this.state;
    const { imaginiProdus } = this.state;
    const { token } = this.props;

    const base64Array = await Promise.all(
      imaginiProdus.map(url => base64(url))
    );
    const imagePaths = await this.uploadImages(base64Array);
    console.log("imagepaths:", imagePaths);

    const options = {
      method: "PUT",
      body: JSON.stringify({
        ...produs,
        dataPostare: null,
        imaginiProdus: imagePaths.join(",") || produs.imaginiProdus
      }),

      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      }
    };

    await fetch("http://localhost:8888/produs/updateProdus", options)
      .then(() => this.setState({ salvareSuccess: true }))
      .catch(err => this.setState({ error: true }));
  };

  render() {
    console.log("imaginiProdusNoi:", this.state.imaginiProdus);

    const { error } = this.state;
    const { imaginiProdus } = this.state;

    return (
      <div className="container-mare-editare-produs">
        <h1 className="heading-editare-produs">Editare produs</h1>

        <div>
          <label className="label-editare-produs">Titlu anunt:</label>
          <input
            value={this.state.produs.numeProdus}
            onChange={this.changeTitlu}
            type="text"
            name="titlu"
            className="editare-titlu-produs"
          />
        </div>

        <div>
          <label className="label-editare-produs">Pret:</label>
          <input
            value={this.state.produs.pretProdus}
            onChange={this.changePret}
            type="number"
            name="pret"
            className="editare-pret-produs"
          />
        </div>

        <div>
          <label className="label-editare-produs">Brand:</label>
          <input
            value={this.state.produs.brandProdus}
            onChange={this.changeBrand}
            type="text"
            name="brand"
            className="editare-brand-produs"
          />
        </div>

        <div>
          <label className="label-editare-produs">Transport:</label>
          <select
            onChange={this.changeTransport}
            value={this.state.produs.transportGratuit}
            name="transport"
            id="1"
            className="editare-transport-produs"
          >
            <option value="true">Inclus</option>
            <option value="false">Neinclus</option>
          </select>
        </div>

        <div>
          <label className="label-editare-produs">Stare:</label>
          <select
            onChange={this.changeStare}
            name="stare"
            id="1"
            className="editare-stare-produs"
          >
            <option value="1">Nou</option>
            <option value="2">Excelent</option>
            <option value="3">Foarte bun</option>
            <option value="4">Bun</option>
            <option value="5">Acceptabil</option>
            <option value="6">Deteriorat</option>
          </select>
        </div>

        <div>
          <label className="label-editare-descriere-produs">Descriere:</label>
          <textarea
            value={this.state.produs.descriereProdus}
            onChange={this.changeDescriere}
            name=""
            id=""
            rows="10"
            className="editare-descriere-produs"
          />
        </div>

        <div className="imagini-editare-produs">
          {imaginiProdus.map(imagine => (
            <div
              mama={imagine}
              key={imagine}
              style={{ backgroundImage: `url(${imagine})` }}
            />
          ))}
        </div>

        <div>
          <div className="imagine-text">Incarca o imagine:</div>
          <ImageUploader
            withIcon={true}
            buttonText="Choose images"
            onChange={this.onDrop}
            imgExtension={[".jpg", ".gif", ".png", ".gif"]}
            maxFileSize={5242880}
          />
          <div className="div-editare-produs">
            <button
              type="submit"
              className="buton-editeaza-produs"
              onClick={this.editeazaProdus}
            >
              Salveaza datele
            </button>
            {this.state.salvareSuccess && <Redirect to="/contulMeu" />}
            {this.state.error && (
              <div className="eroare">Nu s-a putut edita utilizatorul</div>
            )}
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(EditareProdus);
