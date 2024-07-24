import React from 'react'
import { View, Image, Text, StyleSheet } from 'react-native'
import { BASE_URL } from '@env'
import { IPizza } from '@/interfaces/pizza'
import { Link } from 'expo-router'
import { ThemedView } from '@/components/ThemedView'
import { ThemedText } from '@/components/ThemedText'

interface PizzaCardProps {
  pizza: IPizza
}

const PizzaCard: React.FC<PizzaCardProps> = ({ pizza }) => {
  return (
    <View style={styles.card}>
      <Image
        source={{ uri: `${BASE_URL}images/200_${pizza.photos[0].name}` }}
        style={styles.image}
      />
      <View style={styles.infoContainer}>
        <Link href={`/pizza/${pizza.id}`} style={styles.title}>
          {pizza.name}
        </Link>

        <ThemedText style={styles.price}>${100}</ThemedText>
        <ThemedText style={styles.availability}>
          {pizza.isAvailable ? 'Available' : 'Not Available'}
        </ThemedText>
      </View>
    </View>
  )
}

const styles = StyleSheet.create({
  card: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 10,
    marginVertical: 5,
    backgroundColor: '#fff',
    borderRadius: 10,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 5,
    elevation: 2,
  },
  image: {
    width: 60,
    height: 60,
    borderRadius: 30,
  },
  infoContainer: {
    flex: 1,
    marginLeft: 10,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
  },
  description: {
    fontSize: 14,
    color: '#666',
  },
  rating: {
    fontSize: 14,
    color: '#666',
  },
  price: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#333',
  },
  availability: {
    fontSize: 14,
    color: '#666',
  },
})

export default PizzaCard
