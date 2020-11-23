import { Component } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import Transactions from './Transactions';
import Graph from './Graph';

export default class Main extends Component {
	constructor(props) {
		super(props);
		
		this.state = {amounts: []};

		this.graphCallback = this.graphCallback.bind(this);
	}

	graphCallback(amounts) {
		console.warn(amounts);
		this.setState({amounts});
	}

	render() {
		return (
			<div id = "main" className = "main">
				<Container fluid>
					<Row>
						<Col xs = {12} sm = {12} md = {8} lg = {8}>
							<Transactions callback = {this.graphCallback}/>
						</Col>
						<Col xs = {12} sm = {12} md = {4} lg = {4}>
							<Graph amounts = {this.state.amounts}/>
						</Col>
					</Row>
				</Container>
			</div>
		);
	}
}
