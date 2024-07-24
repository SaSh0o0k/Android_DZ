import React from 'react'
import { View, Image, Text, StyleSheet } from 'react-native'
import { BASE_URL } from '@env'
import { ICategory } from '@/interfaces/category'
import { Link } from 'expo-router'
import { ThemedView } from '@/components/ThemedView'

interface CategoryItemProps {
  category: ICategory
}
const CategoryCard: React.FC<CategoryItemProps> = ({ category }) => {
  return (
    <View style={styles.container}>
      <Image
        source={{ uri: `${BASE_URL}images/200_${category.image}` }}
        style={styles.categoryImage}
      />
      <ThemedView style={styles.textContainer}>
        <Link href={`/category/${category.id}`} style={styles.title}>
          {category.name}
        </Link>
      </ThemedView>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    alignItems: 'center',
    marginBottom: 20,
    position: 'relative',
  },
  categoryImage: {
    width: 200,
    height: 200,
    borderRadius: 10,
  },
  textContainer: {
    position: 'absolute',
    top: '40%',
    left: 0,
    right: 0,
    backgroundColor: 'rgba(5,0,0,0.5)',
    padding: 10,
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#ffffff',
    textAlign: 'center',
  },
})
export default CategoryCard
