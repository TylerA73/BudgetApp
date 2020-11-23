import { Component } from 'react';
import {Container, Col, Row, Card } from 'react-bootstrap';
import {
  PieChart, Pie, Sector, Cell, Legend
} from 'recharts';

const COLORS = {
	"Income": "#0088FE",
	"Essential": "#00C49F",
	"Non-Essential": "#FFBB28"
};

export default class Graph extends Component {
	constructor(props) {
		super(props);

		this.getTotal = this.getTotal.bind(this);
	}

	getTotal(data) {
		let tot = 0;
		if (data.length > 0) {
			data.forEach((amount) => {
				if (amount.category.id != 1) {
					tot -= amount.amount;
				} else {
					tot += amount.amount;
				}
			})
		}

		if (tot < 0) {
			return (
				<h3 className = "negative">${ tot }</h3>
			);
		} else if (tot > 0) {
			return (
				<h3 className = "positive">${ tot }</h3>
			);
		} else {
			return (
				<h3 className = "even">${ tot }</h3>
			);
		}
	}
	
	render() {
		let data = this.props.amounts.map((entry, index) => {
			return {
				name: entry.category.description,
				value: entry.amount
			}
		})
		return (
			<Container fluid className = "budget-card">
				<Row>
					
					<Col xs = {12} sm = {12} md = {6} lg = {6}>
						<div className = "budget-card">
							<PieChart width={400} height={250}>
								<Pie
									data={data}
									innerRadius={60}
									outerRadius={80}
									fill="#8884d8"
									paddingAngle={5}
									dataKey="value"
									label
								>
									{
										data.map((entry, index) => <Cell key={`cell-${index}`} fill={COLORS[entry.name]} />)
									}
								</Pie>
								<Legend verticalAlign = "bottom" />
							</PieChart>
						</div>
					</Col>
					<Col xs = {12} sm = {12} md = {6} lg = {6}>
						<div className = "total">
							{ this.getTotal(this.props.amounts) }
						</div>
					</Col>
				</Row>
			</Container>
		);
	}
}
